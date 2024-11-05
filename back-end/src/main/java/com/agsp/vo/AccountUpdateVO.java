package com.agsp.vo;

import com.agsp.enumerator.BancoEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AccountUpdateVO(
		
		@NotNull(message = "O preenchimento do campo ID é obrigatório")
		Long id,
		
//		@Positive(message = "Saldo deve ser um valor positivo")
//		BigDecimal saldo,
		
		BancoEnum banco
		
		
		) {

}
