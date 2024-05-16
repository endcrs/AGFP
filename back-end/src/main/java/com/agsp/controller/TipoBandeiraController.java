package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.TipoBandeiraService;
import com.agsp.vo.TipoBandeiraVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/tipos-bandeira")
@RequiredArgsConstructor
public class TipoBandeiraController {
	
	private final TipoBandeiraService tipoBandeiraService;
	
	@GetMapping()
	public List<TipoBandeiraVO> recuperarTiposBandeiras () {
		return tipoBandeiraService.recuperarTiposBandeiras();
	}

}
