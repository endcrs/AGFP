package com.agsp.vo;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioPutVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O preenchimento do campo ID é obrigatório")
	private Long id;
	
	@Size(min = 4, max = 30, message = "O campo nome deve ter no mínimo 4 caracteres máximo 30")
	private String nome;
	
	@Size(min = 4, max = 30, message = "O campo sobre deve ter no mínimo 4 caracteres máximo 30")
	private String sobrenome;

	@PastOrPresent(message = "A data de nascimento não pode ser uma data futura")
	private LocalDate dataNascimento;
	
	@Size(max = 11, min = 11, message = "O campo celular deve ter 11 caracteres")
	private String celular;
	
}
