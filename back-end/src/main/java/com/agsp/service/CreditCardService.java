package com.agsp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.entity.CreditCardEntity;
import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.factory.CartaoEntityFactory;
import com.agsp.exception.DadosJaCadastradosException;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CreditCardRepository;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.CardVO;
import com.agsp.vo.CreditCardUpdateVO;
import com.agsp.vo.CreditCardVO;
import com.agsp.vo.factory.CreditCardVOFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {
	
//	private final UserService usuarioService;
	private final CreditCardRepository creditCardRepository;
	private final CurrentAccountRepository currentAccountRepository;
	
	@Transactional
	public CreditCardVO creatCard(CreditCardVO vo) {
		
//		validarIdUsuario(vo);
		validateAccountId(vo);
		validateCreditCarNumber(vo.numero());
		
		CurrentAccountEntity account = getLinkedAccount(vo.idConta());
		
//		UserEntity usuario = usuarioService.getUserEntity(vo.idUsuario());
		
//		validarSaldo(usuario, vo.limite());
		
		CreditCardEntity creditCard = CartaoEntityFactory.converterParaEntity(account, vo);
		
		creditCard = creditCardRepository.save(creditCard);
		
		return CreditCardVOFactory.converterParaVO(creditCard);
	}
	
	@Transactional
	public CreditCardUpdateVO updateCard(CreditCardUpdateVO vo) {
		
//		validarIdUsuario(vo);
//		validateAccountId(vo);
		validateCreditCardId(vo);
//		validateCreditCarNumber(vo.numero());
		
		CreditCardEntity creditCard = getCreditCard(vo.getId());
		
//		validarSaldo(usuario, vo.limite());
		
//		CreditCardEntity creditCard = CartaoEntityFactory.converterParaEntity(account, vo);
		
		creditCard = creditCardRepository.save(creditCard);
		
//		creditCard.set
		
//		return CreditCardVOFactory.converterParaVO(creditCard);
		return CreditCardUpdateVO.builder()
				.id(creditCard.getId())
				.limite(creditCard.getLimite())
				.build();
	}
	
//	private void validarSaldo(UsuarioEntity usuario, BigDecimal limite) {
//		if(limite.longValue() > usuario.getSaldo().longValue()) {
//			throw new MsgException("Valor do limite do cartão maior que saldo disponivel do usuário");
//		}
//	}

	private void validateCreditCardId(CreditCardUpdateVO vo) {
		if(vo.getId() == null) {
			throw new MsgException("É obrigatório o preenchimento do campo ID");
		}
	}

	private void validateAccountId(CreditCardVO vo) {
		if(vo.idConta() == null) {
			throw new MsgException("É obrigatório o preenchimento do campo ID conta");
		}
	}

	private CurrentAccountEntity getLinkedAccount(Long accoutId) {
		return currentAccountRepository.findById(accoutId)
					.orElseThrow(() -> new NaoEncontradoException("Conta não encontrada"));
	}

	private void validateCreditCarNumber(String numero) {
		if(creditCardRepository.existsByNumero(numero)) 
			throw new DadosJaCadastradosException (Constantes.CARTAO_CADASTRADO_COM_NUMERO);
	}

//	private void validarIdUsuario(CreditCardVO vo) {
//		if(vo.idUsuario() == null) {
//			throw new MsgException("É obrigatório o preenchimento do campo ID usuário");
//		}
//	}

	public List<CardVO> getCreditCardByCpf(String cpf) {
		
//		List<CartaoCreditoEntity> cartoes = cartaoRepository.findByUsuarioCpf(cpf);
		
//		return CartaoVOFactory.converterListParaVO(cartoes);
		return new ArrayList<>();
	}

	public CardVO getCreditCardById(Long cardId) {
		CreditCardEntity card = getCreditCard(cardId);
		return CreditCardVOFactory.toVO(card);
	}
	
	public CreditCardEntity getCreditCard(Long cardId) {
		return creditCardRepository.findById(cardId)
				.orElseThrow(() -> new NaoEncontradoException("Cartão crédito não encontrado"));
}

	@Transactional
	public void disableCard(Long cardId) {
		CreditCardEntity card = getCreditCard(cardId);
		
		if(Boolean.FALSE.equals(card.getAtivo())){
			throw new MsgException("Cartão já foi desativado");
		}
		
		card.setAtivo(Boolean.FALSE);
		creditCardRepository.save(card);
	}

	public List<CardVO> getCreditsCardByUserid(Long usuarioId) {
		
		List<CreditCardEntity> cards = creditCardRepository.findCardByUserId(usuarioId);
		
		List<CardVO> vos = new ArrayList<>();
		
		cards.forEach( c -> {
			
			vos.add(CardVO.builder()
					.id(c.getId())
					.facturaAtual(c.getFacturaAtual())
					.numero(c.getNumero())
					.nome(c.getNome())
					.bandeira(c.getBandeira())
					.validade(c.getValidade())
					.limite(c.getLimite())
					.vencimento(c.getVencimento())
					.build());
		});
		
		return vos;
	}
	
//	public CartaoCreditoEntity recuperarPorNumero(String numeroCartao) {
//		return cartaoRepository.findByNumero(numeroCartao)
//				.orElseThrow(() -> new NaoEncontradoException("Cartão com número "+numeroCartao + " não encontarado"));
//	}

}
