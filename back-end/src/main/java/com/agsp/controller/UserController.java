package com.agsp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.UserService;
import com.agsp.vo.LoginVO;
import com.agsp.vo.UsuarioVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Conjunto de endpoints para gerenciar o fluxo do Usuário.")
public class UserController {
	
	private final UserService usuarioService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para efectuar login do usuário já cadastrado no sistema.", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Login efectuado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioVO.class))),
    })
	public UsuarioVO login (@RequestBody @Valid LoginVO vo) {
		return usuarioService.login(vo);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint do cadastro usuário.", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Cadastro do usuário efectuado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioVO.class))),
    })
	public UsuarioVO save (@RequestBody @Valid UsuarioVO vo) {
		return usuarioService.save(vo);
	}
	
//	@ResponseStatus(HttpStatus.OK)
//	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @Operation(summary = "Endpoint da edição do cadastro do usuário.", responses = {
//            @ApiResponse(responseCode = "201",
//                    description = "Usuario editado com sucesso",
//                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = UsuarioVO.class))),
//    })
//	public UsuarioVO edit (@RequestBody @Valid UsuarioPutVO vo) {
//		return usuarioService.edit(vo);
//	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para recuperação dos dados do usuário.", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario recuperado comcom sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioVO.class))),
    })
	public UsuarioVO getUser (@PathVariable Long id) {
		return usuarioService.getUser(id);
	}
}
