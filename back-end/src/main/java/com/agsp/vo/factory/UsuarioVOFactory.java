package com.agsp.vo.factory;

import java.math.BigDecimal;

import com.agsp.entity.UsuarioEntity;
import com.agsp.vo.UsuarioVO;

public class UsuarioVOFactory {
	
	private UsuarioVOFactory() {}
	
	public static UsuarioVO converterParaVO(UsuarioEntity entity, BigDecimal saldo) {
		if(entity != null) {
			return UsuarioVO.builder()
					.id(entity.getId())
					.cpf(entity.getCpf())
					.celular(entity.getCelular())
					.dataNascimento(entity.getDataNascimento())
					.nomeCompleto(entity.getNomeCompleto())
					.saldo(saldo)
					.build();
		} else 
			return null;
	}
	

}
