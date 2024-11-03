package com.agsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agsp.service.TransacaoService;
import com.agsp.vo.TransaListVO;
import com.agsp.vo.TransacaoVO;
import com.agsp.vo.TransactionCurrentAccountVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value ="/transactions")
@RequiredArgsConstructor
public class TrasactionController {
	
	private final TransacaoService transacaoService;
	
//	@PostMapping()
	public TransacaoVO salvar (@RequestBody @Valid TransacaoVO vo) {
		return transacaoService.salvar(vo);
	}
	
//	@GetMapping(value = "/listar-todos")
	public List<TransaListVO> listarTodos (@RequestParam(value = "cpf", required = true) String cpf) {
		return transacaoService.listarTodasTransacoes(cpf);
	}
	
//	@GetMapping(value = "/listar-percentagem-gasto-categoria")
//	public CategoriaListVO listarPercentagemGastoCategoria (@RequestParam(value = "cpf", required = true) String cpf) {
//		return transacaoService.listarPercentagemGastoCategoria(cpf);
//	}
	
//	@GetMapping(value = "/mensais")
	public List<TransaListVO> listarTransacoesMensais (@RequestParam(value = "cpf", required = true) String cpf) {
		return transacaoService.listarTransacoesMensais(cpf);
	}
	
	@PutMapping(value ="/disable-transaction/{id}")
	public void disableCard(@PathVariable(value ="id") Long id) {
		transacaoService.disableTransaction(id);
	}
	
	@GetMapping(value = "/{usuarioId}")
	public List<TransactionCurrentAccountVO> getTransactions (@PathVariable(value = "usuarioId") Long usuarioId) {
		return transacaoService.getAllTransactions(usuarioId);
	}
	

}
