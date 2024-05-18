package com.agsp.vo.factory;

import com.agsp.entity.TransacaoEntity;
import com.agsp.vo.TransacaoVO;

public class TransacaoVOFactory {
	
	private TransacaoVOFactory() {}

	public static TransacaoVO converterParaVO(TransacaoEntity transacao) {
		
		if(transacao != null) {
			return TransacaoVO.builder()
					.id(transacao.getId())
					.valor(transacao.getValorCompra())
					.titulo(transacao.getTitulo())
					.categoria(transacao.getCategoria())
					.build();
		} else 
			return null;
	}

}
