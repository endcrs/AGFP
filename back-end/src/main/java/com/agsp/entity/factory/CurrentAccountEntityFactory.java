package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.vo.AccountVO;

public class CurrentAccountEntityFactory {

	public static CurrentAccountEntity convertToEntity(AccountVO account, UserEntity user) {
		
		if(account != null) {
			return CurrentAccountEntity.builder()
					.dataCadastro(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.saldo(account.saldo())
					.banco(account.banco())
					.usuario(user)
					.build();
		} else {
			return 
				 null;
		}
	}

}
