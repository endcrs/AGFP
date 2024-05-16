package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.CategoriaService;
import com.agsp.vo.CategoriaVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/categorias")
@RequiredArgsConstructor
public class CatergoriaController {
	
	private final CategoriaService categoriaService;
	
	@GetMapping()
	public List<CategoriaVO> recuperarCategorias() {
		return categoriaService.recuperarCategorias();
	}

}
