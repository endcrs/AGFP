package com.agsp.service;

import org.springframework.stereotype.Service;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.entity.factory.CurrentAccountEntityFactory;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.repository.UserRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.AccountOwnerVO;
import com.agsp.vo.AccountVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final UserRepository userRepository;
	private final CurrentAccountRepository currentAccountRepository;
	
	@Transactional
	public AccountVO createdAccount(AccountVO vo) {
		
		UserEntity user = userRepository.findById(vo.idUsuario()).get();
		
		CurrentAccountEntity account = CurrentAccountEntityFactory.convertToEntity(vo, user);
		
		currentAccountRepository.save(account);
		
		return AccountVO.builder()
				.id(account.getId())
				.saldo(account.getSaldo())
				.banco(account.getBanco())
				.build();
	}

	public AccountVO getAccount(Long id) {
		
		CurrentAccountEntity account = getCurrentAccountEntity(id);
		
		return AccountVO.builder()
				.id(id)
				.banco(account.getBanco())
				.saldo(account.getSaldo())
				.usuario(AccountOwnerVO.builder()
						.id(id)
						.nome(account.getUsuario().getNome()+" "+account.getUsuario().getSobrenome())
						.build())
				.dataCriacao(account.getDataCadastro())
				.build();
	}
	
	private CurrentAccountEntity getCurrentAccountEntity(Long id) {
		return currentAccountRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constantes.CONTA_NAO_ENCONTRADO));
	}

}
