package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.entity.CartaoEntity;
import com.agsp.entity.UsuarioEntity;
import com.agsp.entity.factory.CartaoEntityFactory;
import com.agsp.exception.DadosJaCadastradosException;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CartaoRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.CartaoVO;
import com.agsp.vo.CartoesVO;
import com.agsp.vo.factory.CartaoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {
	
	private final UsuarioService usuarioService;
	private final CartaoRepository cartaoRepository;
	
	public CartaoVO salvar(CartaoVO vo) {
		
		validarIdUsuario(vo);
		
		validarNumero(vo.numero());
		
		UsuarioEntity usuario = usuarioService.recuperarUsuario(vo.idUsuario());
		
//		validarSaldo(usuario, vo.limite());
		
		CartaoEntity cartaoEntity = CartaoEntityFactory.converterParaEntity(usuario, vo);
		
		cartaoEntity = cartaoRepository.save(cartaoEntity);
		
		return CartaoVOFactory.converterParaVO(cartaoEntity);
	}
	
//	private void validarSaldo(UsuarioEntity usuario, BigDecimal limite) {
//		if(limite.longValue() > usuario.getSaldo().longValue()) {
//			throw new MsgException("Valor do limite do cartão maior que saldo disponivel do usuário");
//		}
//	}

	private void validarNumero(String numero) {
		
		boolean existsByNumero = cartaoRepository.existsByNumero(numero);
		
		if(existsByNumero) 
			throw new DadosJaCadastradosException(Constantes.CARTAO_CADASTRADO_COM_NUMERO);
	}

	private void validarIdUsuario(CartaoVO vo) {
		
		if(vo.idUsuario() == null) {
			throw new MsgException("É obrigatório o preenchimento do campo ID usuário");
		}
	}

	public List<CartoesVO> recuperarCartoesUsurio(String cpf) {
		
		List<CartaoEntity> cartoes = cartaoRepository.findByUsuarioCpf(cpf);
		
		return CartaoVOFactory.converterListParaVO(cartoes);
	}
	
	public CartaoEntity recuperarPorNumero(String numeroCartao) {
		return cartaoRepository.findByNumero(numeroCartao)
				.orElseThrow(() -> new NaoEncontradoException("Cartão com número "+numeroCartao + " não encontarado"));
	}

}
