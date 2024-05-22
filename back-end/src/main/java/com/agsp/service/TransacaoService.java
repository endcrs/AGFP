package com.agsp.service;

import org.springframework.stereotype.Service;

import com.agsp.entity.CartaoEntity;
import com.agsp.entity.TransacaoEntity;
import com.agsp.entity.factory.TransacaoEntityFactory;
import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.repository.CartaoRepository;
import com.agsp.repository.TransacaoRespository;
import com.agsp.vo.TransacaoVO;
import com.agsp.vo.factory.TransacaoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransacaoService {
	
	private final TransacaoRespository transacaoRespository;
	private final CartaoRepository cartaoRepository;
//	private final UsuarioRepository usuarioRepository;
	
	private final CartaoService cartaoService;
//	private final UsuarioService usuarioService;
	
	public TransacaoVO salvar(TransacaoVO vo) {
		
		CartaoEntity cartao = cartaoService.recuperarPorNumero(vo.getNumeroCartao());
		
//		UsuarioEntity usuario = usuarioService.recuperarUsuario(cartao.getUsuario().getId());
		
		TransacaoEntity transacao = TransacaoEntityFactory.converterParaEntity(cartao, vo);
		
		cartao.setSaldoDisponivel(TipoTransacaoEnum.isDespesa(vo.getTipoTransacao())
				? cartao.getSaldoDisponivel().subtract(vo.getValor())
						: cartao.getSaldoDisponivel().add(vo.getValor()));
//		if(TipoTransacaoEnum.isDespesa(vo.getTipoTransacao())) {
			
//			validarValorSaldoTransacaoDebito(usuario, vo.getValor());
			
			
//		} else if (TipoTransacaoEnum.isReceita(vo.getTipoTransacao())) {
			
//			validarValorTransacao(cartao, vo.getValor());
			
//			cartao.setLimiteDisponivel(cartao.getLimiteTotal().subtract(vo.getValor()));
			
			cartaoRepository.save(cartao);
//		}
		
		transacao = transacaoRespository.save(transacao);
		return TransacaoVOFactory.converterParaVO(transacao);
	}

//	private void validarValorTransacao(CartaoEntity cartao, BigDecimal valorCompra) {
//		if(valorCompra.longValue() > cartao.getLimiteTotal().longValue()) {
//			throw new MsgException("Valor da compra maior que limite disponivel no cartão");
//		}
//	}
	
//	private void validarValorSaldoTransacaoDebito(UsuarioEntity usuario, BigDecimal valorCompra) {
//		if(valorCompra.longValue() > usuario.getSaldo().longValue()) {
//			throw new MsgException("Valor da compra maior que saldo disponivel do usuário");
//		}
//	}

}
