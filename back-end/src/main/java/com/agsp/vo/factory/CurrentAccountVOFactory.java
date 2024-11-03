package com.agsp.vo.factory;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.vo.AccountOwnerVO;
import com.agsp.vo.AccountVO;

public class CurrentAccountVOFactory {

	public static AccountVO toVO(CurrentAccountEntity account) {
		if(account != null) {
			return AccountVO.builder()
					.id(account.getId())
					.banco(account.getBanco())
					.saldo(account.getSaldo())
					.usuario(AccountOwnerVO.builder()
							.id(account.getUsuario().getId())
							.nome(getFullName(account.getUsuario()))
							.build())
					.dataCriacao(account.getDataCadastro())
					.build();
		} return
				null;
	}

	private static String getFullName(UserEntity usuario) {
		return usuario != null ? usuario.getNome() +" "+usuario.getSobrenome() : "";
	}

}
