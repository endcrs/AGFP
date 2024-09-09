package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.entity.UsuarioEntity;
import com.agsp.vo.UsuarioPutVO;
import com.agsp.vo.UsuarioVO;

public class UsuarioEntityFactory {
	
	private UsuarioEntityFactory() {}

	public static UsuarioEntity converterParaEntity(UsuarioVO vo) {
		
		if(vo != null) {
			return UsuarioEntity.builder()
					.nome(vo.getNome())
					.sobrenome(vo.getSobrenome())
					.cpf(vo.getCpf().trim())
					.senha(vo.getSenha())
					.dataNascimento(vo.getDataNascimento())
					.celular(vo.getCelular())
					.dataCadastro(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.build();
		} else 
			return null;
	}
	

	public static void atualizarUsuario(UsuarioPutVO vo, UsuarioEntity usuarioBanco) {
		
		usuarioBanco.setNome(vo.getNome() != null ? vo.getNome() : usuarioBanco.getNome());
		usuarioBanco.setSobrenome(vo.getSobrenome() != null ? vo.getSobrenome() : usuarioBanco.getSobrenome());
		usuarioBanco.setDataNascimento(vo.getDataNascimento() != null ? vo.getDataNascimento() : usuarioBanco.getDataNascimento());
		usuarioBanco.setCelular(vo.getCelular() != null ? vo.getCelular() : usuarioBanco.getCelular());
	}

}
