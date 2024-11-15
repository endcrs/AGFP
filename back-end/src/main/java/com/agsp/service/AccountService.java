package com.agsp.service;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.entity.factory.CurrentAccountEntityFactory;
import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.repository.TransactionRepository;
import com.agsp.repository.UserRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.AccountOwnerVO;
import com.agsp.vo.AccountResponseVO;
import com.agsp.vo.AccountUpdateVO;
import com.agsp.vo.AccountVO;
import com.agsp.vo.SaldoAtualVO;
import com.agsp.vo.factory.CurrentAccountVOFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final UserRepository userRepository;
	private final TransactionRepository transactionRespository;
	private final UserRepository usuarioRepository;
	private final CurrentAccountRepository currentAccountRepository;
	
	@Transactional
	public AccountVO createAccount(AccountVO vo) {
		
		validateBank(vo);
		
		UserEntity user = userRepository.findById(vo.idUsuario()).get();
		
		CurrentAccountEntity account = CurrentAccountEntityFactory.convertToEntity(vo, user);
		
		currentAccountRepository.save(account);
		
		return AccountVO.builder()
				.id(account.getId())
				.saldo(account.getSaldo())
				.banco(account.getBanco())
				.build();
	}
	
	
	private void validateBank(AccountVO vo) {
		if(currentAccountRepository.findByUsuarioId(vo.idUsuario())
				.stream().filter( a -> a.getBanco().equals(vo.banco())).findFirst().isPresent()) {
			throw new MsgException("Já existe uma conta deste usúario para tipo do banco informado");
		}
	}


	@Transactional
	public AccountVO updateAccount(AccountUpdateVO vo) {
		
		CurrentAccountEntity account = getCurrentAccountEntity(vo.id());
		
//		if(vo.saldo() != null) {
//			validateCurrentAccountTransaction(account.getId());
//		}
		
//		account.setSaldo(vo.saldo() != null ? vo.saldo() : account.getSaldo());
		account.setBanco(vo.banco() != null ? vo.banco() : account.getBanco() );

		currentAccountRepository.save(account);
		
		return AccountVO.builder()
				.id(account.getId())
				.saldo(account.getSaldo())
				.banco(account.getBanco())
				.dataCriacao(account.getDataCadastro())
				.usuario(AccountOwnerVO.builder()
						.id(account.getUsuario().getId())
						.nome(getFullName(account.getUsuario()))
						.build())
				.build();
	}

//	private void validateCurrentAccountTransaction(Long accountId) {
//		
//		if(transacaoRespository.hasTransationCurrentAccount(accountId).size() >= 1) {
//			throw new MsgException("Não foi possivel atualizar saldo da conta uma vez que tem transação vinculada");
//		}
//		
//	}


	public AccountResponseVO getAccount(Long id) {
		
		CurrentAccountEntity account = getCurrentAccountEntity(id);
		
		return CurrentAccountVOFactory.toVO(account);
	}
	
	private String getFullName(UserEntity usuario) {
		return usuario.getNome()+" "+usuario.getSobrenome();
	}


	private CurrentAccountEntity getCurrentAccountEntity(Long id) {
		return currentAccountRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.CONTA_NAO_ENCONTRADO));
	}


	public List<AccountResponseVO> getAccounts(Long usuarioId) {
		
		List<CurrentAccountEntity> accounts = currentAccountRepository.findByUsuarioId(usuarioId);
		
//		accounts.removeIf(t -> t.getSaldo().longValue() <= 0);
		
		List<AccountResponseVO> vos = new ArrayList<>();
		
		accounts.forEach(a -> {
			
			vos.add(CurrentAccountVOFactory.toVO(a));
		});
		return vos;
	}
	
	public SaldoAtualVO monthlyExpenses(Long userId) {
		
		UserEntity usuario =  getUserEntity(userId);
		
		ZonedDateTime dataFim = getToday();
		ZonedDateTime dataInicio = getToday().withDayOfMonth(1);
		
		BigDecimal saldoAtual = currentAccountRepository.getTotalSaldoMensal(usuario.getId());
		BigDecimal despesa = transactionRespository.getTotalMonthlyExpensesOrRevenues
				(usuario.getId(), dataInicio, dataFim, TipoTransacaoEnum.DESPESA);
		BigDecimal receita = transactionRespository.getTotalMonthlyExpensesOrRevenues
				(usuario.getId(), dataInicio, dataFim, TipoTransacaoEnum.RECEITA);
		
		return SaldoAtualVO.builder()
				.saldoAtual(saldoAtual != null ? saldoAtual : new BigDecimal(0.0))
				.despesa(despesa != null ?  despesa : new BigDecimal(0.0))
				.receita(receita != null ? receita : new BigDecimal(0.0))
				.lucro(getLucrovalue(receita, despesa))
				.build();
	}
	
	private BigDecimal getLucrovalue(BigDecimal receita, BigDecimal despesa) {
		
		BigDecimal lucro = new BigDecimal(0.0);
		if(receita != null && despesa != null) {
			lucro = receita.subtract(despesa);
		}
		
		return lucro;
	}


	private ZonedDateTime getToday() {
		return ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

	public UserEntity getUserEntity(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}

}
