package com.agsp.vo.factory;

import com.agsp.entity.TransationEntity;
import com.agsp.vo.DominioVO;
import com.agsp.vo.TransactionCurrentAccountVO;

public class TransactionCurrentAccountVOFcatory {

	public static TransactionCurrentAccountVO convertToVO(TransationEntity t) {
		
		if(t != null) {
			return TransactionCurrentAccountVO.builder()
					.id(t.getId())
					.categoria(t.getCategoria())
					.status(t.getStatus())
					.titulo(t.getTitulo())
					.valor(t.getValorCompra())
					.tipoTransacao(DominioVO.builder()
							.codigo(t.getTipo().name())
							.descricao(t.getTipo().getDescricao())
							.build())
					.categoriaTransacao(DominioVO.builder()
							.codigo(t.getCategoria().name())
							.descricao(t.getCategoria().getDescricao())
							.build())
					.dataTransacao(t.getDataTransacao())
					.build();
		}return 
				null;
	}
	
	

}
