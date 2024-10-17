package com.agsp.vo;

import java.math.BigDecimal;

import com.agsp.enumerator.BancoEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AccountUpdateVO(
		
		@NotNull(message = "O preenchimento do campo ID é obrigatório")
		Long id,
		
		@Positive(message = "Saldo deve ser um valor positivo")
		BigDecimal saldo,
		
		BancoEnum banco
		
		
		) {

}
