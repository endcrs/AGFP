package com.agsp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.UserEntity;
import com.agsp.entity.factory.CurrentAccountEntityFactory;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.repository.UserRepository;
import com.agsp.util.Constantes;
import com.agsp.vo.AccountOwnerVO;
import com.agsp.vo.AccountUpdateVO;
import com.agsp.vo.AccountVO;
import com.agsp.vo.factory.CurrentAccountVOFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final UserRepository userRepository;
//	private final TransactionRepository transacaoRespository;
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


	public AccountVO getAccount(Long id) {
		
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


	public List<AccountVO> getAccounts(Long usuarioId) {
		
		List<CurrentAccountEntity> accounts = currentAccountRepository.findByUsuarioId(usuarioId);
		
		//melhorar isso depois 
		List<AccountVO> vos = new ArrayList<>();
		accounts.forEach(a -> {
			vos.add(CurrentAccountVOFactory.toVO(a));
		});
		return vos;
	}

}
