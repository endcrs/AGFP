package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.TipoBandeiraService;
import com.agsp.vo.DominioVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/flag-types")
@RequiredArgsConstructor
public class TipoBandeiraController {
	
	private final TipoBandeiraService tipoBandeiraService;
	
	@GetMapping()
	public List<DominioVO> getFlagTypes () {
		return tipoBandeiraService.getFlagTypes();
	}

}
