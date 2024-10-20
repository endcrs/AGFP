package com.agsp.controller;

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
import com.agsp.vo.TransactionVO;

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
	
	@PostMapping(value = "/transaction")
	public TransactionVO createTransaction (@RequestBody @Valid TransactionVO transaction) {
		return accountTransactionService.createTransaction(transaction);
	}
	
	@PutMapping(value = "/transaction")
	public TransactionVO updateTransaction (@RequestBody @Valid TransactionVO transaction) {
		return accountTransactionService.updateTransaction(transaction);
	}
	

}
