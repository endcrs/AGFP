package com.agsp.service;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agsp.entity.LoginUsuarioEntity;
import com.agsp.entity.UsuarioEntity;
import com.agsp.entity.factory.UsuarioEntityFactory;
import com.agsp.exception.DadosJaCadastradosException;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CartaoRepository;
import com.agsp.repository.LoginUsuarioRepository;
import com.agsp.repository.UsuarioRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.LoginVO;
import com.agsp.vo.UsuarioPutVO;
import com.agsp.vo.UsuarioVO;
import com.agsp.vo.factory.UsuarioVOFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final CartaoRepository cartaoRepository;
	private final UsuarioRepository usuarioRepository;
	private final LoginUsuarioRepository loginUsuarioRepository;
	
	@Transactional
	public UsuarioVO login(LoginVO vo) {
		
		UsuarioEntity usuario = recuperarUsuarioPorCpf(vo.cpf());
		
		validarLogin(usuario.getSenha(), vo.senha());
		
		registrarLogin(usuario);
		
		BigDecimal saldo = getSaldoDisponivel(usuario);
		
		return UsuarioVOFactory.converterParaVO(usuario, saldo);
	}

	private BigDecimal getSaldoDisponivel(UsuarioEntity usuario) {
		BigDecimal saldo =  cartaoRepository.findSaldoTotalCartoesUsuario(usuario.getId());
		return saldo != null ? saldo : BigDecimal.valueOf(0.0);
	}

	@Transactional
	public UsuarioVO salvar(UsuarioVO vo) {
		
		validarSenhaCadastroAtualizacao(vo);
		
		validarCpf(vo.getCpf());
		
		UsuarioEntity usuarioEntity = usuarioRepository.save(UsuarioEntityFactory.converterParaEntity(vo));
		
		return UsuarioVOFactory.converterParaVO(usuarioEntity, getSaldoDisponivel(usuarioEntity));
	}
	
	public UsuarioVO editar(UsuarioPutVO vo) {
		
		UsuarioEntity usuarioBanco = recuperarUsuario(vo.getId());
		
		UsuarioEntityFactory.atualizarUsuario(vo, usuarioBanco);
		
		usuarioRepository.save(usuarioBanco);
		
		BigDecimal saldo = getSaldoDisponivel(usuarioBanco);
		
		return UsuarioVOFactory.converterParaVO(usuarioBanco, saldo);
	}
	
	private void validarCpf(String cpf) {
		if(usuarioRepository.existsByCpf(cpf)) 
			throw new DadosJaCadastradosException(Constantes.CPF_CDASTRADO);
	}
	
	private void validarSenhaCadastroAtualizacao(UsuarioVO vo) {
		if(!vo.getSenha().equals(vo.getSenhaConfirmada())) {
			throw new MsgException(Constantes.SENHAS_DIFERENTES);
		}
	}
	
	private void validarLogin(String senhaBanco, String senhaDigitada){
		if(!senhaBanco.equals(senhaDigitada)) {
			throw new MsgException(Constantes.SENHA_INVALIDA);
		}
	}
	
	private void registrarLogin(UsuarioEntity usuario) {
		
		loginUsuarioRepository.save(LoginUsuarioEntity.builder()
				.dataHora(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
				.usuario(usuario)
				.token(UUID.randomUUID().toString())
				.build());
	}
	
	public UsuarioEntity recuperarUsuario(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}
	
	private UsuarioEntity recuperarUsuarioPorCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}

	public UsuarioVO recuperar(Long id) {
		UsuarioEntity usuario =  recuperarUsuario(id);
		return UsuarioVOFactory.converterParaVO(usuario, getSaldoDisponivel(usuario));
	}
}
