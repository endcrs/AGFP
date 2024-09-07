package com.agsp.entity.factory;

import com.agsp.entity.CartaoCreditoEntity;
import com.agsp.entity.TransacaoEntity;
import com.agsp.vo.TransacaoVO;

public class TransacaoEntityFactory {
	
	
	private TransacaoEntityFactory() {}

	public static TransacaoEntity converterParaEntity(CartaoCreditoEntity cartao, TransacaoVO vo) {
		
		if(cartao != null && vo != null) {
			return TransacaoEntity.builder()
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
