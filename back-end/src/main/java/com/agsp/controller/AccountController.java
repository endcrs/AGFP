package com.agsp.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.AccountService;
import com.agsp.service.AccountTransactionService;
import com.agsp.vo.AccountResponseVO;
import com.agsp.vo.AccountUpdateVO;
import com.agsp.vo.AccountVO;
import com.agsp.vo.CategoriaListVO;
import com.agsp.vo.SaldoAtualVO;
import com.agsp.vo.TransactionCurrentAccountResponseVO;
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
	public AccountResponseVO createdAccount (@PathVariable(value = "id") Long id) {
		return accountService.getAccount(id);
	}
	
	@GetMapping(value = "/by-user/{usuarioId}")
	public List<AccountResponseVO> getAccounts (@PathVariable(value = "usuarioId") Long usuarioId) {
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
	
	@GetMapping(value = "/transactions/{userId}")
	public List<TransactionCurrentAccountResponseVO> getTransactions (@PathVariable(value = "userId") Long userId) {
		return accountTransactionService.getTransactions(userId);
	}
	
	@GetMapping(value = "/transactions/monthly/{userId}")
	public List<TransactionCurrentAccountResponseVO> getMensalTransactions (@PathVariable(value = "userId") Long userId) {
		return accountTransactionService.getMensalTransactions(userId);
	}
	
	@GetMapping(value = "/transactions/percentage-spent-by-category/{userId}")
	public CategoriaListVO listPercentageSpentByCategory (@PathVariable(value = "userId") Long userId) {
		return accountTransactionService.listPercentageSpentByCategory(userId);
	}
	
	@GetMapping(value = "/monthly-expenses/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SaldoAtualVO getDespesaMensal (@PathVariable Long userId) {
		return accountService.monthlyExpenses(userId);
	}
	
	@GetMapping(value = "/transactions/{accountId}")
	public List<TransactionCurrentAccountResponseVO> getTransactionsOfAccount (@PathVariable(value = "accountId") Long accountId) {
		return accountTransactionService.getTransactionsOfAccount(accountId);
	}
}
