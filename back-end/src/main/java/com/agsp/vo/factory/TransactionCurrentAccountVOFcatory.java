package com.agsp.vo.factory;

import com.agsp.entity.CreditCardEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.vo.AccountVO;
import com.agsp.vo.CardVO;
import com.agsp.vo.DominioVO;
import com.agsp.vo.TransactionCurrentAccountResponseVO;

public class TransactionCurrentAccountVOFcatory {

	public static TransactionCurrentAccountResponseVO convertToVO(TransationEntity t, CreditCardEntity creditcard) {
		
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
					.nomeCartao(creditcard.getNome())
					.conta(t.getTransacao().equals("CONTA") ? 
							AccountVO.builder()
								.id(t.getCurrentAccount().getId())
								.banco(t.getCurrentAccount().getBanco())
								.build() : null )
					.cartao(t.getTransacao().equals("CARTAO") ?
							CardVO.builder()
								.id(creditcard.getId())
								.nome(creditcard.getNome())
							.build() : null )
					.build();
		}return 
				null;
	}
	
	

}
