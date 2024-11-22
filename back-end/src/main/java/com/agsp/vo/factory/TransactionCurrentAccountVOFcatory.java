package com.agsp.vo.factory;

import com.agsp.entity.TransationEntity;
import com.agsp.vo.AccountVO;
import com.agsp.vo.DominioVO;
import com.agsp.vo.TransactionCurrentAccountResponseVO;

public class TransactionCurrentAccountVOFcatory {

	public static TransactionCurrentAccountResponseVO convertToVO(TransationEntity t, String nomeCartao) {
		
		if(t != null) {
			return TransactionCurrentAccountResponseVO.builder()
					.id(t.getId())
					.status(t.getStatus())
					.titulo(t.getTitulo())
					.valor(t.getValorCompra())
					.tipo(DominioVO.builder()
							.codigo(t.getTipo().name())
							.descricao(t.getTipo().getDescricao())
							.build())
					.categoria(DominioVO.builder()
							.codigo(t.getCategoria().name())
							.descricao(t.getCategoria().getDescricao())
							.build())
					.dataTransacao(t.getDataTransacao())
					.transacao(t.getTransacao())
					.nomeCartao(nomeCartao)
					.conta(AccountVO.builder()
							.id(t.getCurrentAccount().getId())
							.banco(t.getCurrentAccount().getBanco())
							.build())
					.build();
		}return 
				null;
	}
	
	

}
