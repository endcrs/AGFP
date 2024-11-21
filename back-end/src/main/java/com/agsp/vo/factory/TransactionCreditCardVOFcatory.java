package com.agsp.vo.factory;

import com.agsp.entity.CreditCardTansationEntity;
import com.agsp.vo.CardVO;
import com.agsp.vo.DominioVO;
import com.agsp.vo.TransactionCreditCardResponseVO;

public class TransactionCreditCardVOFcatory {

	public static TransactionCreditCardResponseVO convertToVO(CreditCardTansationEntity creditCardT) {
		if(creditCardT != null) {
			return TransactionCreditCardResponseVO.builder()
					.id(creditCardT.getTransation().getId())
					.status(creditCardT.getTransation().getStatus())
					.titulo(creditCardT.getTransation().getTitulo())
					.valor(creditCardT.getTransation().getValorCompra())
					.tipo(DominioVO.builder()
							.codigo(creditCardT.getTransation().getTipo().name())
							.descricao(creditCardT.getTransation().getTipo().getDescricao())
							.build())
					.categoria(DominioVO.builder()
							.codigo(creditCardT.getTransation().getCategoria().name())
							.descricao(creditCardT.getTransation().getCategoria().getDescricao())
							.build())
					.dataTransacao(creditCardT.getTransation().getDataTransacao())
					.cartao(CardVO.builder()
							.id(creditCardT.getCreditCard().getId() )
							.nome(creditCardT.getCreditCard().getNome())
							.build())
					.build();
		}return 
				null;
	}

}
