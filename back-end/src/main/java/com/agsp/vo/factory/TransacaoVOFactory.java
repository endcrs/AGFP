package com.agsp.vo.factory;

import com.agsp.entity.TransacaoEntity;
import com.agsp.vo.TransacaoVO;

public class TransacaoVOFactory {
	
	private TransacaoVOFactory() {}

	public static TransacaoVO converterParaVO(TransacaoEntity transacao) {
		
		if(transacao != null) {
			return TransacaoVO.builder()
					.id(transacao.getId())
					.titulo(transacao.getTitulo())
					.valor(transacao.getValorCompra())
					.categoria(transacao.getCategoria())
					.dataTransacao(transacao.getDataTransacao())
					.tipoTransacao(transacao.getTipoTransacao())
					.build();
		} else 
			return null;
	}

}
