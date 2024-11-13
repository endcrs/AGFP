package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.enumerator.CategoriaEnum;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.vo.TransactionCreditCardVO;
import com.agsp.vo.TransactionCurrentAccountVO;

public class TransacaoEntityFactory {
	
	private TransacaoEntityFactory() {}

	public static TransationEntity convertTransactionToEntity(TransactionCurrentAccountVO tAccount, 
			TransactionCreditCardVO tCard, CurrentAccountEntity account) {
		
		
		CategoriaEnum  categoria = null;
		
		if(tAccount != null && account != null) {
			categoria = TipoTransacaoEnum.isReceita(tAccount.getTipo()) ? CategoriaEnum.NULA : tAccount.getCategoria();
			return TransationEntity.builder()
					.ativo(Boolean.TRUE)
					.dataTransacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.status(StatusEnum.ATIVO)
					.categoria(categoria)
					.titulo(tAccount.getTitulo())
					.valorCompra(tAccount.getValor())
					.tipo(tAccount.getTipo())
					.currentAccount(account != null ? account : null)
					.transacao("CONTA")
					.build();
		} else 
			categoria = TipoTransacaoEnum.isReceita(tCard.getTipo()) ? CategoriaEnum.NULA : tCard.getCategoria();
			return TransationEntity.builder()
					.ativo(Boolean.TRUE)
					.dataTransacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.status(StatusEnum.ATIVO)
					.categoria(categoria)
					.valorCompra(tCard.getValor())
					.tipo(tCard.getTipo())
					.titulo(tCard.getTitulo())
					.currentAccount(account)
					.transacao("CARTAO")
					.build();
	}

}
