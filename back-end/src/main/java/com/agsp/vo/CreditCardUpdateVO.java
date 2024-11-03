package com.agsp.vo;

import java.math.BigDecimal;

import com.agsp.enumerator.TipoBandeiraEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardUpdateVO {
	
	@NotNull(message = "O prenchimento do campo ID é obrigatório")
	private Long id;
	
	private TipoBandeiraEnum bandeira;
	
	@Positive(message = "Limite não pode ser valor negativo")
	BigDecimal limite;
	
	@Size(max = 16, min = 16,  message = "O campo numero deve ter 16 caracteres. Exemplo 0000.0000.0000.0000")
	private String numero;
	
	@Size(max = 5, min = 5,  message = "O campo vencimento deve ter 4 caracteres. Exemplo 05/24")
	private String validade;
	
	Integer vencimento;
	
}
