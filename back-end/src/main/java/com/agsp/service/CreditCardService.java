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
import com.agsp.vo.CreditCardVO;
import com.agsp.vo.factory.CreditCardVOFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {
	
//	private final UserService usuarioService;
	private final CreditCardRepository creditCarRepository;
	private final CurrentAccountRepository currentAccountRepository;
	
	public CreditCardVO creatCard(CreditCardVO vo) {
		
//		validarIdUsuario(vo);
		validateAccountId(vo);
		validateCreditCarNumber(vo.numero());
		
		CurrentAccountEntity account = getLinkedAccount(vo.idConta());
		
//		UserEntity usuario = usuarioService.getUserEntity(vo.idUsuario());
		
//		validarSaldo(usuario, vo.limite());
		
		CreditCardEntity creditCard = CartaoEntityFactory.converterParaEntity(account, vo);
		
		creditCard = creditCarRepository.save(creditCard);
		
		return CreditCardVOFactory.converterParaVO(creditCard);
	}
	
//	private void validarSaldo(UsuarioEntity usuario, BigDecimal limite) {
//		if(limite.longValue() > usuario.getSaldo().longValue()) {
//			throw new MsgException("Valor do limite do cartão maior que saldo disponivel do usuário");
//		}
//	}

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
		boolean existsByNumero = creditCarRepository.existsByNumero(numero);
		if(existsByNumero) 
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
		return creditCarRepository.findById(cardId)
				.orElseThrow(() -> new NaoEncontradoException("Cartão crédito não encontrado"));
}

	@Transactional
	public void disableCard(Long cardId) {
		CreditCardEntity card = getCreditCard(cardId);
		
		if(Boolean.FALSE.equals(card.getAtivo())){
			throw new MsgException("Cartão já foi desativado");
		}
		
		card.setAtivo(Boolean.FALSE);
		creditCarRepository.save(card);
	}
	
//	public CartaoCreditoEntity recuperarPorNumero(String numeroCartao) {
//		return cartaoRepository.findByNumero(numeroCartao)
//				.orElseThrow(() -> new NaoEncontradoException("Cartão com número "+numeroCartao + " não encontarado"));
//	}

}
