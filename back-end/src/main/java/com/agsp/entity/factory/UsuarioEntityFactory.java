package com.agsp.entity.factory;

import com.agsp.entity.UsuarioEntity;
import com.agsp.vo.UsuarioPutVO;
import com.agsp.vo.UsuarioVO;

public class UsuarioEntityFactory {
	
	private UsuarioEntityFactory() {}

	public static UsuarioEntity converterParaEntity(UsuarioVO vo) {
		
		if(vo != null) {
			return UsuarioEntity.builder()
//					.nomeCompleto(vo.getNomeCompleto())
//					.cpf(vo.getCpf().trim())
//					.senha(vo.getSenha())
//					.senhaConfirmada(vo.getSenhaConfirmada())
//					.dataNascimento(vo.getDataNascimento())
//					.celular(vo.getCelular())
//					.dataCadastro(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.build();
		} else 
			return null;
	}
	

	public static void atualizarUsuario(UsuarioPutVO vo, UsuarioEntity usuarioBanco) {
		
//		usuarioBanco.setNomeCompleto(vo.getNomeCompleto() != null ? vo.getNomeCompleto() : usuarioBanco.getNomeCompleto());
//		usuarioBanco.setDataNascimento(vo.getDataNascimento() != null ? vo.getDataNascimento() : usuarioBanco.getDataNascimento());
//		usuarioBanco.setCelular(vo.getCelular() != null ? vo.getCelular() : usuarioBanco.getCelular());
//		usuarioBanco.setDataAtualizacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)));
	}

}
