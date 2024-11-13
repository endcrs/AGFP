package com.agsp.vo.factory;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.vo.AccountOwnerVO;
import com.agsp.vo.AccountResponseVO;
import com.agsp.vo.DominioVO;

public class CurrentAccountVOFactory {

	public static AccountResponseVO toVO(CurrentAccountEntity account) {
		if(account != null) {
			return AccountResponseVO.builder()
					.id(account.getId())
					.saldo(account.getSaldo())
					.usuario(AccountOwnerVO.builder()
							.id(account.getUsuario().getId())
							.nome(getFullName(account.getUsuario()))
							.build())
					.dataCriacao(account.getDataCadastro())
					.banco(DominioVO.builder()
							.id(null)
							.codigo(account.getBanco().name())
							.descricao(account.getBanco().getDescricao())
							.build())
					.build();
		} return
				null;
	}

	private static String getFullName(UserEntity usuario) {
		return usuario != null ? usuario.getNome() +" "+usuario.getSobrenome() : "";
	}

}
