package com.agsp.vo;

import java.math.BigDecimal;

import com.agsp.enumerator.TipoBandeiraEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreditCardVO(
		
		Long id,
		
		@NotBlank(message = "O prenchimento do campo número é obrigatório")
		@Size(max = 16, min = 16,  message = "O campo numero deve ter 16 caracteres. Exemplo 0000.0000.0000.0000")
		String numero,
		
		@NotNull(message = "O prenchimento do campo limite é obrigatório")
		@Positive(message = "Limite não pode ser valor negativo")
		BigDecimal limite,
		
		@NotNull(message = "O prenchimento do campo validade é obrigatório")
		@Size(max = 5, min = 5,  message = "O campo vencimento deve ter 4 caracteres. Exemplo 05/24")
		String validade,
		
		@NotNull(message = "O prenchimento do campo bandeira é obrigatório")
		TipoBandeiraEnum bandeira,
		
		@NotNull(message = "O prenchimento do campo vencimento é obrigatório")
		Integer vencimento,
		
		@NotNull(message = "O prenchimento do ID conta é obrigatório")
		Long idConta
		
//		@NotNull(message = "O prenchimento do ID usuário é obrigatório")
//		Long idUsuario
		
		) {

}
