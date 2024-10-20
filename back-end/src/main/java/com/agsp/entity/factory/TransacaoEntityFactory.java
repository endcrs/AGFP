package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.enumerator.StatusEnum;
import com.agsp.vo.TransactionVO;

public class TransacaoEntityFactory {
	
	private TransacaoEntityFactory() {}

	public static TransationEntity converterParaEntity(TransactionVO vo, CurrentAccountEntity account) {
		
		if(vo != null) {
			return TransationEntity.builder()
					.ativo(Boolean.TRUE)
					.dataTransacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.status(StatusEnum.ATIVO)
					.categoria(vo.getCategoria())
					.valorCompra(vo.getValor())
					.currentAccount(account)
					.build();
		} else 
			return null;
	}

}
