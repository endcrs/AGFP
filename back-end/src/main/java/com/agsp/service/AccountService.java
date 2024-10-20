package com.agsp.service;

import org.springframework.stereotype.Service;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.entity.factory.CurrentAccountEntityFactory;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.repository.TransactionRepository;
import com.agsp.repository.UserRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.AccountOwnerVO;
import com.agsp.vo.AccountUpdateVO;
import com.agsp.vo.AccountVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final UserRepository userRepository;
	private final TransactionRepository transacaoRespository;
	private final CurrentAccountRepository currentAccountRepository;
	
	@Transactional
	public AccountVO createAccount(AccountVO vo) {
		
		UserEntity user = userRepository.findById(vo.idUsuario()).get();
		
		CurrentAccountEntity account = CurrentAccountEntityFactory.convertToEntity(vo, user);
		
		currentAccountRepository.save(account);
		
		return AccountVO.builder()
				.id(account.getId())
				.saldo(account.getSaldo())
				.banco(account.getBanco())
				.build();
	}
	
	
	@Transactional
	public AccountVO updateAccount(AccountUpdateVO vo) {
		
		CurrentAccountEntity account = getCurrentAccountEntity(vo.id());
		
		if(vo.saldo() != null) {
			validateCurrentAccountTransaction(account.getId());
		}
		
		account.setSaldo(vo.saldo() != null ? vo.saldo() : account.getSaldo());
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

	private void validateCurrentAccountTransaction(Long accountId) {
		
		if(transacaoRespository.hasTransationCurrentAccount(accountId).size() >= 1) {
			throw new MsgException("Não foi possivel atualizar saldo da conta uma vez que tem transação vinculada");
		}
		
	}


	public AccountVO getAccount(Long id) {
		
		CurrentAccountEntity account = getCurrentAccountEntity(id);
		
		return AccountVO.builder()
				.id(id)
				.banco(account.getBanco())
				.saldo(account.getSaldo())
				.usuario(AccountOwnerVO.builder()
						.id(id)
						.nome(getFullName(account.getUsuario()))
						.build())
				.dataCriacao(account.getDataCadastro())
				.build();
	}
	
	private String getFullName(UserEntity usuario) {
		return usuario.getNome()+" "+usuario.getSobrenome();
	}


	private CurrentAccountEntity getCurrentAccountEntity(Long id) {
		return currentAccountRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.CONTA_NAO_ENCONTRADO));
	}

}
