package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.TipoBancoService;
import com.agsp.vo.DominioVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/tipos-bancos")
@RequiredArgsConstructor
public class TipoBancoController {
	
	private final TipoBancoService tipoBancoService;
	
	@GetMapping()
	public List<DominioVO> recuperarTiposConta () {
		return tipoBancoService.recuperarTipoConta();
	}

}
