package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.CreditCardService;
import com.agsp.service.CreditCardTransactionService;
import com.agsp.vo.CardVO;
import com.agsp.vo.CategoriaListVO;
import com.agsp.vo.CreditCardUpdateVO;
import com.agsp.vo.CreditCardVO;
import com.agsp.vo.TransactionCreditCardResponseVO;
import com.agsp.vo.TransactionCreditCardVO;
import com.agsp.vo.TransactionCurrentAccountVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/cards")
@RequiredArgsConstructor
public class CreditCardController {
	
	private final CreditCardService creditCardService;
	private final CreditCardTransactionService creditCardTransactionService;
	
	@PostMapping()
	public CreditCardVO createCard (@RequestBody @Valid CreditCardVO vo) {
		return creditCardService.creatCard(vo);
	}
	
//	@PutMapping()
	public CreditCardUpdateVO updateCard (@RequestBody @Valid CreditCardUpdateVO vo) {
		return creditCardService.updateCard(vo);
	}
	
//	@GetMapping()
	public List<CardVO> getCreditCardsByCpf(@RequestParam(required = true, value = "cpf") String cpf) {
		return creditCardService.getCreditCardByCpf(cpf);
	}
	
	@GetMapping(value ="/{id}")
	public CardVO getCreditCardById(@PathVariable(value ="id") Long id) {
		return creditCardService.getCreditCardById(id);
	}
	
	@GetMapping(value = "/by-user/{usuarioId}")
	public List<CardVO> getCreditsCardByUserid(@PathVariable(value ="usuarioId") Long usuarioId) {
		return creditCardService.getCreditsCardByUserid(usuarioId);
	}
	
//	@PutMapping(value ="/disable-card/{id}")
	public void disableCard(@PathVariable(value ="id") Long id) {
		creditCardService.disableCard(id);
	}
	
	@PostMapping(value = "/transactions")
	public TransactionCurrentAccountVO createTransaction (@RequestBody @Valid TransactionCreditCardVO transaction) {
		return creditCardTransactionService.createTransaction(transaction);
	}
	
//	@PutMapping(value = "/transaction")
//	public TransactionCurrentAccountVO updateTransaction (@RequestBody @Valid TransactionCurrentAccountVO transaction) {
//		return creditCardTransactionService.updateTransaction(transaction);
//	}
	
	@GetMapping(value = "/transactions/{userId}")
	public List<TransactionCreditCardResponseVO> getTransactions (@PathVariable(value = "userId") Long userId) {
		return creditCardTransactionService.getTransactions(userId);
	}
	
	@GetMapping(value = "/transactions/monthly/{userId}")
	public List<TransactionCreditCardResponseVO> getMensalTransactions (@PathVariable(value = "userId") Long userId) {
		return creditCardTransactionService.getMensalTransactions(userId);
	}
	
	@GetMapping(value = "/transactions/percentage-spent-by-category/{userId}")
	public CategoriaListVO listPercentageSpentByCategory (@PathVariable(value = "userId") Long userId) {
		return creditCardTransactionService.listPercentageSpentByCategory(userId);
	}
	
	@GetMapping(value = "/transactions/{cardId}")
	public List<TransactionCreditCardResponseVO> getTransactionsByCreditCard (@PathVariable(value = "cardId") Long cardId) {
		return creditCardTransactionService.getTransactionsByCreditCard(cardId);
	}

}
