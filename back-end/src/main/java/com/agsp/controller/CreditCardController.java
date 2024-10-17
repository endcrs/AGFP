package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.CreditCardService;
import com.agsp.vo.CardVO;
import com.agsp.vo.CreditCardVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/cards")
@RequiredArgsConstructor
public class CreditCardController {
	
	private final CreditCardService creditCardService;
	
	@PostMapping()
	public CreditCardVO salvar (@RequestBody @Valid CreditCardVO vo) {
		return creditCardService.creatCard(vo);
	}
	
//	@GetMapping()
	public List<CardVO> getCreditCardsByCpf(@RequestParam(required = true, value = "cpf") String cpf) {
		return creditCardService.getCreditCardByCpf(cpf);
	}
	
	@GetMapping(value ="/{id}")
	public CardVO getCreditCardById(@PathVariable(value ="id") Long id) {
		return creditCardService.getCreditCardById(id);
	}
	
	@PutMapping(value ="/disable-card/{id}")
	public void disableCard(@PathVariable(value ="id") Long id) {
		creditCardService.disableCard(id);
	}

}
