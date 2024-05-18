package com.agsp.vo;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginVO(
		
		@NotBlank(message = "O preenchimento do campo CPF é obrigatório")
		@CPF(message = "CPF inválido")
		@Size(max = 11, min = 11, message = "O campo CPF deve ter 11 caracteres")
		String cpf,
		
		@NotBlank(message = "O preenchimento do campo senha é obrigatório")
		@Size(min = 8, max = 16, message = "O campo senha deve ter no mínimo 8 caracteres máximo 16")
		String senha
		
		) {
	
}
