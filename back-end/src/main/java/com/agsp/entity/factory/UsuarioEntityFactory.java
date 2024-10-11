package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.entity.UserEntity;
import com.agsp.vo.UsuarioPutVO;
import com.agsp.vo.UsuarioVO;

public class UsuarioEntityFactory {
	
	private UsuarioEntityFactory() {}

	public static UserEntity converterParaEntity(UsuarioVO vo) {
		
		if(vo != null) {
			return UserEntity.builder()
					.ativo(Boolean.TRUE)
					.dataNascimento(vo.getDataNascimento())
					.dataCadastro(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.celular(vo.getCelular())
					.cpf(vo.getCpf().trim())
					.senha(vo.getSenha())
					.nome(vo.getNome())
					.sobrenome(vo.getSobrenome())
					.build();
		} else 
			return null;
	}
	

	public static void atualizarUsuario(UsuarioPutVO vo, UserEntity usuarioBanco) {
		
		usuarioBanco.setNome(vo.getNome() != null ? vo.getNome() : usuarioBanco.getNome());
		usuarioBanco.setSobrenome(vo.getSobrenome() != null ? vo.getSobrenome() : usuarioBanco.getSobrenome());
		usuarioBanco.setDataNascimento(vo.getDataNascimento() != null ? vo.getDataNascimento() : usuarioBanco.getDataNascimento());
		usuarioBanco.setCelular(vo.getCelular() != null ? vo.getCelular() : usuarioBanco.getCelular());
	}

}
