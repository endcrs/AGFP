package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.service.BancoService;
import com.agsp.vo.DominioVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/bancos")
@RequiredArgsConstructor
public class BancoController {
	
	private final BancoService bancoService;
	
//	@GetMapping()
	public List<DominioVO> recuperarBancos(@RequestParam(value="tipo", required = true) TipoBancoEnum tipo) {
		return bancoService.recuperarBancos(tipo);
	}

}
