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
					.nomeCompleto(vo.getNomeCompleto())
					.cpf(vo.getCpf().trim())
					.senha(vo.getSenha())
					.senhaConfirmada(vo.getSenhaConfirmada())
					.dataNascimento(vo.getDataNascimento())
					.celular(vo.getCelular())
					.saldo(vo.getSaldo())
					.dataCadastro(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.build();
		} else 
			return null;
	}
	

	public static void atualizarUsuario(UsuarioPutVO vo, UsuarioEntity usuarioBanco) {
		
		if(vo.getSenha() != null && vo.getSenha().length() > 8) {
			usuarioBanco.setSenha(vo.getSenha());
			usuarioBanco.setSenhaConfirmada(vo.getSenha());
		}
		
		usuarioBanco.setNomeCompleto(vo.getNomeCompleto() != null ? vo.getNomeCompleto() : usuarioBanco.getNomeCompleto());
		usuarioBanco.setDataNascimento(vo.getDataNascimento() != null ? vo.getDataNascimento() : usuarioBanco.getDataNascimento());
		usuarioBanco.setDataAtualizacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)));
	}

}
