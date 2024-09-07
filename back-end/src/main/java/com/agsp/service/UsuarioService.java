package com.agsp.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.agsp.entity.UsuarioEntity;
import com.agsp.entity.factory.UsuarioEntityFactory;
import com.agsp.exception.DadosJaCadastradosException;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
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
	
//	private final CartaoRepository cartaoRepository;
	private final UsuarioRepository usuarioRepository;
//	private final TransacaoRespository transacaoRespository;
//	private final LoginUsuarioRepository loginUsuarioRepository;
	
	@Transactional
	public UsuarioVO login(LoginVO loginVo) {
		
		UsuarioEntity usuario = recuperarUsuarioPorCpf(loginVo.cpf());
		
//		validarLogin(usuario.getSenha(), loginVo.senha());
//		
//		registrarLogin(usuario);
		
		BigDecimal saldo = getSaldoDisponivel(usuario);
		
		BigDecimal receita = getTotalReceitaMensal(usuario);
		BigDecimal despesa = getTotalDespesasMensal(usuario);
		BigDecimal lucro = receita.subtract(despesa);
		
		UsuarioVO vo = UsuarioVOFactory.converterParaVO(usuario, saldo);
		
		vo.setReceita(receita);
		vo.setDespesas(despesa);
		vo.setLucro(lucro);
		
		return vo;
	}

	private BigDecimal getSaldoDisponivel(UsuarioEntity usuario) {
//		BigDecimal saldo =  cartaoRepository.findSaldoTotalCartoesUsuario(usuario.getId());
//		return saldo != null ? saldo : BigDecimal.valueOf(0.0);
		return BigDecimal.valueOf(0.0);
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
	
//	private void validarLogin(String senhaBanco, String senhaDigitada){
//		if(!senhaBanco.equals(senhaDigitada)) {
//			throw new MsgException(Constantes.SENHA_INVALIDA);
//		}
//	}
	
//	private void registrarLogin(UsuarioEntity usuario) {
//		
//		loginUsuarioRepository.save(LoginUsuarioEntity.builder()
//				.dataHora(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
//				.usuario(usuario)
//				.token(UUID.randomUUID().toString())
//				.build());
//	}
	
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
		
		UsuarioVO vo = UsuarioVOFactory.converterParaVO(usuario, getSaldoDisponivel(usuario));
		
		BigDecimal receita = getTotalReceitaMensal(usuario);
		BigDecimal despesa = getTotalDespesasMensal(usuario);
		BigDecimal lucro = receita.subtract(despesa);
		
		vo.setReceita(receita);
		vo.setDespesas(despesa);
		vo.setLucro(lucro);
		
		return vo;
	}

	private BigDecimal getTotalDespesasMensal(UsuarioEntity usuario) {
		
//		LocalDate dataInicio = getToday().with(TemporalAdjusters.firstDayOfMonth());
		
//		BigDecimal despesa = transacaoRespository
//				.getTotalReceitaOrDespesaMensal(usuario.getCpf().trim(), 
//						dataInicio, getToday(), TipoTransacaoEnum.DESPESA);
//		
//		return despesa != null ? despesa : BigDecimal.valueOf(0.0);
		return BigDecimal.valueOf(0.0);
	}

//	private LocalDate getToday() {
//		return LocalDate.now(ZoneId.of(AMERICA_SAO_PAULO));
//	}

	private BigDecimal getTotalReceitaMensal(UsuarioEntity usuario) {
		
//		LocalDate dataInicio = getToday().with(TemporalAdjusters.firstDayOfMonth());
		
//		BigDecimal receita = transacaoRespository
//				.getTotalReceitaOrDespesaMensal(usuario.getCpf().trim(), 
//						dataInicio, getToday(), TipoTransacaoEnum.RECEITA);
		
//		return receita != null ? receita : BigDecimal.valueOf(0.0);
		return BigDecimal.valueOf(0.0);
	}

}
