package com.agsp.vo.factory;

import java.util.List;

import com.agsp.entity.CartaoEntity;
import com.agsp.vo.CartaoVO;
import com.agsp.vo.CartoesVO;

public class CartaoVOFactory {
	
	private CartaoVOFactory() {}

	public static CartaoVO converterParaVO(CartaoEntity entity) {
		
		if(entity != null) {
			return CartaoVO.builder()
					.id(entity.getId())
					.nome(entity.getNome())
					.numero(entity.getNumero())
					.limite(entity.getLimiteTotal())
					.cvv(entity.getCvv())
					.vencimento(entity.getVencimento())
					.banco(entity.getBanco())
					.bandeira(entity.getBandeira())
					.tipoBanco(entity.getTipobanco())
					.build();
		} else 
			return null;
	}

	
	public static CartoesVO converterParaVOCadastro(CartaoEntity entity) {
		
		if(entity != null) {
			return CartoesVO.builder()
					.id(entity.getId())
					.nome(entity.getNome())
					.numero(entity.getNumero())
					.limite(entity.getLimiteTotal())
					.cvv(entity.getCvv())
					.vencimento(entity.getVencimento())
					.banco(entity.getBanco())
					.bandeira(entity.getBandeira())
					.tipoBanco(entity.getTipobanco())
					.limite(entity.getLimiteDisponivel())
					.build();
		} else 
			return null;
	}
	
	public static List<CartoesVO> converterListParaVO(List<CartaoEntity> cartoes) {
		
		return cartoes.stream().map(CartaoVOFactory::converterParaVOCadastro).toList();
	}
	

}
