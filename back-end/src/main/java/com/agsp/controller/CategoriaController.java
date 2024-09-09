package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.CategoriaService;
import com.agsp.vo.DominioVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/categorias")
@RequiredArgsConstructor
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
//	@GetMapping()
	public List<DominioVO> recuperarCategorias() {
		return categoriaService.recuperarCategorias();
	}

}
