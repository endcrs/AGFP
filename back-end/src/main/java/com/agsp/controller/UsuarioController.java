package com.agsp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.UsuarioService;
import com.agsp.vo.LoginVO;
import com.agsp.vo.UsuarioPutVO;
import com.agsp.vo.UsuarioVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@PostMapping("/login")
	public UsuarioVO login (@RequestBody @Valid LoginVO vo) {
		return usuarioService.login(vo);
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public UsuarioVO salvar (@RequestBody @Valid UsuarioVO vo) {
		return usuarioService.salvar(vo);
	}
	
	@PutMapping()
	public UsuarioVO editar (@RequestBody @Valid UsuarioPutVO vo) {
		return usuarioService.editar(vo);
	}
	
	@GetMapping("/{id}")
	public UsuarioVO recuperar (@PathVariable Long id) {
		return usuarioService.recuperar(id);
	}
	
	

}
