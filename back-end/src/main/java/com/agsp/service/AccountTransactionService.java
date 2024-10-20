package com.agsp.service;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.entity.factory.TransacaoEntityFactory;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.repository.TransactionRepository;
import com.agsp.vo.TransactionVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {
	
	private final TransactionRepository transactionRepository;
	private final CurrentAccountRepository currentAccountRepository;
	
	
	@Transactional
	public TransactionVO createTransaction(TransactionVO transaction) {
		
		CurrentAccountEntity account = currentAccountRepository.findById(transaction.getIdConta()).get();
		
		validateAccountBalanse(account.getSaldo().doubleValue(), transaction.getValor().doubleValue());
		
		TransationEntity save = TransacaoEntityFactory.converterParaEntity(transaction, account); 
				
		transactionRepository.save(save);
		
		debitInCurrentAccount(transaction, account);
		
		return TransactionVO.builder()
				.id(save.getId())
				.valor(save.getValorCompra())
				.build();
	}
	
	private void validateAccountBalanse(double accountValue, double transactionValue) {
		if(accountValue < transactionValue) {
			throw new MsgException("Não foi possivel efectuar a compra valor da compra maior que saldo disponivel");
		}
	}


	@Transactional
	public TransactionVO updateTransaction(TransactionVO vo) {
		
		validateField(vo);
		
		TransationEntity transaction = getTransactionEntity(vo.getId());
		BigDecimal lastValue = transaction.getValorCompra();
		
		validateAccountBalanse(transaction.getCurrentAccount().getSaldo().doubleValue(), vo.getValor().doubleValue());
		
		transaction.setCategoria(vo.getCategoria());
		transaction.setValorCompra(vo.getValor());
		transaction.setDataTransacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)));
				
		transactionRepository.save(transaction);
		
		updateCurrentAccount(vo, transaction.getCurrentAccount(), lastValue);
		
		return TransactionVO.builder()
				.id(transaction.getId())
				.valor(transaction.getValorCompra())
				.build();
	}

	private void updateCurrentAccount(TransactionVO vo, CurrentAccountEntity currentAccount, BigDecimal lastValue) {
		BigDecimal currentValue =  currentAccount.getSaldo().add(lastValue);
		currentAccount.setSaldo(currentValue.subtract(vo.getValor()));
		currentAccountRepository.save(currentAccount);
	}


	private void validateField(TransactionVO transaction) {
		if(transaction.getId() == null) {
			throw new MsgException("O preenchimento do campo ID é obrigatório");
		} 
	}


	private void debitInCurrentAccount(TransactionVO transaction, CurrentAccountEntity account) {
		account.setSaldo(account.getSaldo().subtract(transaction.getValor()));
		currentAccountRepository.save(account);
	}
	
	private TransationEntity getTransactionEntity(Long id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Transação não encontrada"));
	}

}
