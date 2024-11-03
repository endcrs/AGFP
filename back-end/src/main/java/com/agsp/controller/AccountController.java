package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.AccountService;
import com.agsp.service.AccountTransactionService;
import com.agsp.vo.AccountUpdateVO;
import com.agsp.vo.AccountVO;
import com.agsp.vo.CategoriaListVO;
import com.agsp.vo.TransactionCurrentAccountVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/accounts")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	private final AccountTransactionService accountTransactionService;
	
	@PostMapping()
	public AccountVO createdAccount (@RequestBody @Valid AccountVO vo) {
		return accountService.createAccount(vo);
	}
	
	@PutMapping()
	public AccountVO updateAccount (@RequestBody @Valid AccountUpdateVO vo) {
		return accountService.updateAccount(vo);
	}
		
	@GetMapping(value = "/{id}")
	public AccountVO createdAccount (@PathVariable(value = "id") Long id) {
		return accountService.getAccount(id);
	}
	
	@GetMapping(value = "/by-user/{usuarioId}")
	public List<AccountVO> getAccounts (@PathVariable(value = "usuarioId") Long usuarioId) {
		return accountService.getAccounts(usuarioId);
	}
	
	@PostMapping(value = "/transactions")
	public TransactionCurrentAccountVO createTransaction (@RequestBody @Valid TransactionCurrentAccountVO transaction) {
		return accountTransactionService.createTransaction(transaction);
	}
	
	@PutMapping(value = "/transactions")
	public TransactionCurrentAccountVO updateTransaction (@RequestBody @Valid TransactionCurrentAccountVO transaction) {
		return accountTransactionService.updateTransaction(transaction);
	}
	
	@GetMapping(value = "/transactions/{accountId}")
	public List<TransactionCurrentAccountVO> getTransactions (@PathVariable(value = "accountId") Long accountId) {
		return accountTransactionService.getTransactions(accountId);
	}
	
	@GetMapping(value = "/transactions/monthly/{accountId}")
	public List<TransactionCurrentAccountVO> getMensalTransactions (@PathVariable(value = "accountId") Long accountId) {
		return accountTransactionService.getMensalTransactions(accountId);
	}
	
	@GetMapping(value = "/transactions/percentage-spent-by-category/{accountId}")
	public CategoriaListVO listPercentageSpentByCategory (@PathVariable(value = "accountId") Long accountId) {
		return accountTransactionService.listPercentageSpentByCategory(accountId);
	}
	
}
