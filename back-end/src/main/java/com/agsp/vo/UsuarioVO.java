package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O preenchimento do campo nome completo é obrigatório")
	@Size(min = 15, max = 75, message = "O campo nome completo deve ter no mínimo 15 caracteres máximo 75")
	private String nomeCompleto;
	

	@NotBlank(message = "O preenchimento do campo senha é obrigatório")
	@Size(min = 8, max = 25, message = "O campo senha deve ter no mínimo 8 caracteres máximo 25")
	private String senha;
	
	@NotBlank(message = "O preenchimento do campo senha confirmada é obrigatório")
	@Size(min = 8, max = 25, message = "O campo senha confirmada deve ter no mínimo 8 caracteres máximo 25")
	private String senhaConfirmada;
	

	@NotBlank(message = "O preenchimento do campo CPF é obrigatório")
	@CPF(message = "CPF inválido")
	@Size(max = 11, min = 11, message = "O campo CPF deve ter 11 caracteres")
	private String cpf;
	
	@NotNull(message = "O preenchimento do campo data nascimento é obrigatório")
	@PastOrPresent(message = "A data de nascimento não pode ser uma data futura")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O preenchimento do campo celular é obrigatório")
	@Size(max = 11, min = 11, message = "O campo celular deve ter 11 caracteres")
	private String celular;
	
	private BigDecimal saldo;
	private BigDecimal receita;
	private BigDecimal despesas;
	private BigDecimal lucro;
	
}
