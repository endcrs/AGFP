package com.agsp.vo;

import java.math.BigDecimal;

import com.agsp.enumerator.BancoEnum;
import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.enumerator.TipoBandeiraEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CartaoVO(
		
		Long id,
		
		@NotNull(message = "O prenchimento do ID usuário é obrigatório")
		Long idUsuario,
		
		@NotNull(message = "O prenchimento do campo banco é obrigatório")
		BancoEnum banco,
		
		@NotNull(message = "O prenchimento do campo tipo banco é obrigatório")
		TipoBancoEnum tipoBanco,
		
		@NotNull(message = "O prenchimento do campo bandeira é obrigatório")
		TipoBandeiraEnum bandeira,
		
		@NotBlank(message = "O prenchimento do campo vencimento é obrigatório")
		@Size(max = 4, min = 4,  message = "O campo vencimento deve ter 4 caracteres. Exemplo 05/24")
		String vencimento,
		
		@NotNull(message = "O prenchimento do campo saldo é obrigatório")
		BigDecimal saldo,
		
		@NotBlank(message = "O prenchimento do campo nome é obrigatório")
		String nome,
		
		@NotBlank(message = "O prenchimento do campo número é obrigatório")
		@Size(max = 16, min = 16,  message = "O campo numero deve ter 16 caracteres. Exemplo 0000.0000.0000.0000")
		String numero,
		
		@NotBlank(message = "O prenchimento do campo CVV é obrigatório")
		@Size(max = 3, min = 3,  message = "O campo numero deve ter 3 caracteres. Exemplo 111")
		String cvv
		
		) {

}
