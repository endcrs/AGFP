package com.agsp.entity.factory;

import com.agsp.entity.CreditCardEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.vo.TransacaoVO;

public class TransacaoEntityFactory {
	
	
	private TransacaoEntityFactory() {}

	public static TransationEntity converterParaEntity(CreditCardEntity cartao, TransacaoVO vo) {
		
		if(cartao != null && vo != null) {
			return TransationEntity.builder()
//				.titulo(vo.getTitulo())
//				.categoria(vo.getCategoria())
//				.valorCompra(vo.getValor())
//				.dataTransacao(vo.getDataTransacao())
//				.tipoTransacao(vo.getTipoTransacao())
//				.cartao(cartao)
				.build();
		} else return null;
	}

}
