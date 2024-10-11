package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.CartaoCreditoService;
import com.agsp.vo.CartaoVO;
import com.agsp.vo.CartoesVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/cartoes")
@RequiredArgsConstructor
public class CartaoController {
	
	private final CartaoCreditoService cartaoService;
	
//	@PostMapping()
	public CartaoVO salvar (@RequestBody @Valid CartaoVO vo) {
		return cartaoService.salvar(vo);
	}
	
//	@GetMapping()
	public List<CartoesVO> recuperarCartoesUsurio(@RequestParam(required = true, value = "cpf") String cpf) {
		return cartaoService.recuperarCartoesUsurio(cpf);
	}

}
