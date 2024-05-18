package com.agsp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.TransacaoService;
import com.agsp.vo.TransacaoVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/transacoes")
@RequiredArgsConstructor
public class TransacaoController {
	
	private final TransacaoService transacaoService;
	
	@PostMapping()
	public TransacaoVO salvar (@RequestBody @Valid TransacaoVO vo) {
		return transacaoService.salvar(vo);
	}

}
