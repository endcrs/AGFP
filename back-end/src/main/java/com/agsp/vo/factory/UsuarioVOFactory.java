package com.agsp.vo.factory;

import com.agsp.entity.UserEntity;
import com.agsp.vo.UsuarioVO;

public class UsuarioVOFactory {
	
	private UsuarioVOFactory() {}
	
	public static UsuarioVO converterParaVO(UserEntity entity) {
		if(entity != null) {
			return UsuarioVO.builder()
					.id(entity.getId())
					.cpf(entity.getCpf())
					.celular(entity.getCelular())
					.dataNascimento(entity.getDataNascimento())
					.nome(entity.getNome())
					.sobrenome(entity.getSobrenome())
					.build();
		} else 
			return null;
	}
	

}
