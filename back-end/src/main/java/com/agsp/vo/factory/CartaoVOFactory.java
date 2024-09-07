package com.agsp.vo.factory;

import java.util.List;

import com.agsp.entity.CartaoCreditoEntity;
import com.agsp.vo.CartaoVO;
import com.agsp.vo.CartoesVO;

public class CartaoVOFactory {
	
	private CartaoVOFactory() {}

	public static CartaoVO converterParaVO(CartaoCreditoEntity entity) {
		
		if(entity != null) {
			return CartaoVO.builder()
//					.id(entity.getId())
//					.nome(entity.getNome())
//					.numero(entity.getNumero())
//					.saldo(entity.getSaldoDisponivel())
//					.cvv(entity.getCvv())
//					.vencimento(entity.getVencimento())
//					.banco(entity.getBanco())
//					.bandeira(entity.getBandeira())
//					.tipoBanco(entity.getTipobanco())
					.build();
		} else 
			return null;
	}

	
	public static CartoesVO converterParaVOCadastro(CartaoCreditoEntity entity) {
		
		if(entity != null) {
			return CartoesVO.builder()
//					.id(entity.getId())
//					.nome(entity.getNome())
//					.numero(entity.getNumero())
//					.saldo(entity.getSaldoDisponivel())
//					.cvv(entity.getCvv())
//					.vencimento(entity.getVencimento())
//					.banco(entity.getBanco())
//					.bandeira(entity.getBandeira())
//					.tipoBanco(entity.getTipobanco())
					.build();
		} else 
			return null;
	}
	
	public static List<CartoesVO> converterListParaVO(List<CartaoCreditoEntity> cartoes) {
		
		return cartoes.stream().map(CartaoVOFactory::converterParaVOCadastro).toList();
	}
	

}
