package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.TipoTransacaoService;
import com.agsp.vo.DominioVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/tipos-transacao")
@RequiredArgsConstructor
public class TipoTransacaoController {
	
	private final TipoTransacaoService tipoPagamentoService;
	
	//@GetMapping()
	public List<DominioVO> recuperarTiposPagamento() {
		return tipoPagamentoService.recuperarTiposTransacao();
	}

}
