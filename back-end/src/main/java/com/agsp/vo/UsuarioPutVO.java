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
	
	@Size(min = 15, max = 75, message = "O campo nome completo deve ter no mínimo 15 caracteres máximo 75")
	private String nomeCompleto;

	@PastOrPresent(message = "A data de nascimento não pode ser uma data futura")
	private LocalDate dataNascimento;
	
	@Size(max = 11, min = 11, message = "O campo celular deve ter 11 caracteres")
	private String celular;
	
}
