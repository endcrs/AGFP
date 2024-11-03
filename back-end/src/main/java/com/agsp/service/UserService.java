package com.agsp.service;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agsp.entity.LoginUsuarioEntity;
import com.agsp.entity.UserEntity;
import com.agsp.entity.factory.UsuarioEntityFactory;
import com.agsp.exception.DadosJaCadastradosException;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.LoginUsuarioRepository;
import com.agsp.repository.UserRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.LoginVO;
import com.agsp.vo.UsuarioPutVO;
import com.agsp.vo.UsuarioVO;
import com.agsp.vo.factory.UsuarioVOFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
//	private final CartaoRepository cartaoRepository;
	private final UserRepository usuarioRepository;
//	private final TransacaoRespository transacaoRespository;
	private final LoginUsuarioRepository loginUsuarioRepository;
	
	
	@Transactional
	public UsuarioVO login(LoginVO loginVo) {
		
		UserEntity usuario = recuperarUsuarioPorCpf(loginVo.cpf());
		
		validarLogin(usuario.getSenha(), loginVo.senha());
//		
		registrarLogin(usuario);
		
//		BigDecimal saldo = getSaldoDisponivel(usuario);
//		
//		BigDecimal receita = getTotalReceitaMensal(usuario);
//		BigDecimal despesa = getTotalDespesasMensal(usuario);
//		BigDecimal lucro = receita.subtract(despesa);
		
		UsuarioVO vo = UsuarioVOFactory.converterParaVO(usuario);
		
//		vo.setReceita(receita);
//		vo.setDespesas(despesa);
//		vo.setLucro(lucro);
		
		return vo;
	}

//	private BigDecimal getSaldoDisponivel(UsuarioEntity usuario) {
//		BigDecimal saldo =  cartaoRepository.findSaldoTotalCartoesUsuario(usuario.getId());
//		return saldo != null ? saldo : BigDecimal.valueOf(0.0);
//		return BigDecimal.valueOf(0.0);
//	}

	@Transactional
	public UsuarioVO save(UsuarioVO vo) {
		
//		validarSenhaCadastroAtualizacao(vo);
		
		validarCpf(vo.getCpf());
		validarDataNascimento(vo.getDataNascimento());
		
		UserEntity usuarioEntity = usuarioRepository.save(UsuarioEntityFactory.converterParaEntity(vo));
		
		return UsuarioVOFactory.converterParaVO(usuarioEntity);
	}
	
	private void validarDataNascimento(LocalDate dataNascimento) {
		
		if(dataNascimento.isAfter(LocalDate.now())) {
			throw new MsgException("A data do nascimento não pode ser uma data futura");
		}
	}

	public UsuarioVO edit(UsuarioPutVO vo) {
		
		UserEntity usuarioBanco = getUserEntity(vo.getId());
		
		UsuarioEntityFactory.atualizarUsuario(vo, usuarioBanco);
		
		usuarioRepository.save(usuarioBanco);
		
//		BigDecimal saldo = getSaldoDisponivel(usuarioBanco);
		
		return UsuarioVOFactory.converterParaVO(usuarioBanco);
	}
	
	private void validarCpf(String cpf) {
		if(usuarioRepository.existsByCpf(cpf)) 
			throw new DadosJaCadastradosException(Constantes.CPF_CDASTRADO);
	}
	
	private void validarLogin(String senhaBanco, String senhaDigitada){
		if(!senhaBanco.equals(senhaDigitada)) {
			throw new MsgException(Constantes.SENHA_INVALIDA);
		}
	}
	
	private void registrarLogin(UserEntity usuario) {
		
//		LoginUsuarioEntity r =loginUsuarioRepository.save(LoginUsuarioEntity.builder()
//				.dataHora(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
//				.usuario(usuario)
//				.token(UUID.randomUUID().toString())
//				.build());
		
		loginUsuarioRepository.save(LoginUsuarioEntity.builder()
				.dataHora(getZoneDateTimeNow())
				.usuario(usuario)
				.token(UUID.randomUUID().toString())
				.build());
		
//		loginUsuarioRepository.save(r);
//		r.setDataHora(getZoneDateTimeNow());
//		loginUsuarioRepository.saveAndFlush(r);
		
		//System.out.println(r.toString());
	}
	
	private ZonedDateTime getZoneDateTimeNow() {
		return ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

	public UserEntity getUserEntity(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}
	
	private UserEntity recuperarUsuarioPorCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}

	public UsuarioVO getUser(Long id) {
		UserEntity usuario =  getUserEntity(id);
		
		UsuarioVO vo = UsuarioVOFactory.converterParaVO(usuario);
		
//		BigDecimal receita = getTotalReceitaMensal(usuario);
//		BigDecimal despesa = getTotalDespesasMensal(usuario);
//		BigDecimal lucro = receita.subtract(despesa);
		
//		vo.setReceita(receita);
//		vo.setDespesas(despesa);
//		vo.setLucro(lucro);
		
		return vo;
	}

//	private BigDecimal getTotalDespesasMensal(UsuarioEntity usuario) {
		
//		LocalDate dataInicio = getToday().with(TemporalAdjusters.firstDayOfMonth());
		
//		BigDecimal despesa = transacaoRespository
//				.getTotalReceitaOrDespesaMensal(usuario.getCpf().trim(), 
//						dataInicio, getToday(), TipoTransacaoEnum.DESPESA);
//		
//		return despesa != null ? despesa : BigDecimal.valueOf(0.0);
//		return BigDecimal.valueOf(0.0);
//	}

//	private LocalDate getToday() {
//		return LocalDate.now(ZoneId.of(AMERICA_SAO_PAULO));
//	}

//	private BigDecimal getTotalReceitaMensal(UsuarioEntity usuario) {
		
//		LocalDate dataInicio = getToday().with(TemporalAdjusters.firstDayOfMonth());
		
//		BigDecimal receita = transacaoRespository
//				.getTotalReceitaOrDespesaMensal(usuario.getCpf().trim(), 
//						dataInicio, getToday(), TipoTransacaoEnum.RECEITA);
		
//		return receita != null ? receita : BigDecimal.valueOf(0.0);
//		return BigDecimal.valueOf(0.0);
//	}
	
//	private void validarTokenLogin(String token) {
//		
//		LoginUsuarioEntity registoLogin = loginUsuarioRepository.findByToken(token)
//				.orElseThrow(() -> new NaoEncontradoException(Constantes.USARIO_PELO_TOKEN));
//		
//		long minutos = registoLogin.getDataHora().until(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)),
//				ChronoUnit.MINUTES);
//		
//		if(minutos > 10) {
//			throw new MsgException("");
//		}
//		
//	}

}
