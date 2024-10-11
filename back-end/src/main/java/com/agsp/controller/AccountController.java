package com.agsp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.AccountService;
import com.agsp.vo.AccountVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/accounts")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	@PostMapping()
	public AccountVO createdAccount (@RequestBody @Valid AccountVO vo) {
		return accountService.createdAccount(vo);
	}
	
	@GetMapping(value = "/{id}")
	public AccountVO createdAccount (@PathVariable(value = "id") Long id) {
		return accountService.getAccount(id);
	}
	

}
