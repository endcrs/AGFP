package com.agsp.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.agsp.entity.CartaoEntity;
import com.agsp.entity.TransacaoEntity;
import com.agsp.entity.UsuarioEntity;
import com.agsp.entity.factory.TransacaoEntityFactory;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CartaoRepository;
import com.agsp.repository.TransacaoRespository;
import com.agsp.repository.UsuarioRepository;
import com.agsp.vo.TransacaoVO;
import com.agsp.vo.factory.TransacaoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransacaoService {
	
	private final TransacaoRespository transacaoRespository;
	private final CartaoRepository cartaoRepository;
	private final UsuarioRepository usuarioRepository;
	
	public TransacaoVO salvar(TransacaoVO vo) {
		
		CartaoEntity cartao = recuperarPorNumero(vo.getNumeroCartao());
		
		validarValorTransacao(cartao, vo.getValor());
		
		TransacaoEntity transacao = TransacaoEntityFactory.converterParaEntity(cartao, vo);
		
		transacao = transacaoRespository.save(transacao);
		
		cartao.setLimiteDisponivel(cartao.getLimiteTotal().subtract(vo.getValor()));
		
		cartaoRepository.save(cartao);
		
		UsuarioEntity usuario = cartao.getUsuario();
		
		validarValorSaldoTransacao(usuario, vo.getValor());
		
		usuario.setSaldo(usuario.getSaldo().subtract(vo.getValor()));
		
		usuarioRepository.save(usuario);
		
		return TransacaoVOFactory.converterParaVO(transacao);
	}

	private void validarValorTransacao(CartaoEntity cartao, BigDecimal valorCompra) {
		if(valorCompra.longValue() > cartao.getLimiteTotal().longValue()) {
			throw new MsgException("Valor da compra maior que limite disponivel no cartão");
		}
	}
	
	private void validarValorSaldoTransacao(UsuarioEntity usuario, BigDecimal valorCompra) {
		if(valorCompra.longValue() > usuario.getSaldo().longValue()) {
			throw new MsgException("Valor da compra maior que saldo disponivel do usuário");
		}
	}

	private CartaoEntity recuperarPorNumero(String numeroCartao) {
		return cartaoRepository.findByNumero(numeroCartao)
				.orElseThrow(() -> new NaoEncontradoException("Cartão com número "+numeroCartao + " não encontarado"));
	}

}
