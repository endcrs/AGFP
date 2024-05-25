package com.agsp.vo.factory;

import com.agsp.entity.TransacaoEntity;
import com.agsp.vo.DominioVO;
import com.agsp.vo.TransaListVO;
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

	public static TransaListVO converterParaVOList(TransacaoEntity transacao) {
		if(transacao != null) {
			return TransaListVO.builder()
					.id(transacao.getId())
					.categoria(DominioVO.builder()
							.id(transacao.getCategoria().getId())
							.codigo(transacao.getCategoria().name())
							.descricao(transacao.getCategoria().getDescricao())
							.build())
					.tipoTranscao(DominioVO.builder()
							.id(transacao.getTipoTransacao().getId())
							.codigo(transacao.getTipoTransacao().name())
							.descricao(transacao.getTipoTransacao().getDescricao())
							.build())
					.dataTransacao(transacao.getDataTransacao())
					.titulo(transacao.getTitulo())
					.valor(transacao.getValorCompra())
					.build();
		} else 
			return null;
		
	}

}
