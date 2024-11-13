package com.agsp.vo;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.agsp.enumerator.BancoEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AccountVO(
		
		Long id,
		
		@NotNull(message = "O preenchimento do campo ID usuário é obrigatório")
		Long idUsuario,
		
		@NotNull(message = "O preenchimento do campo saldo é obrigatório")
		@Positive(message = "Saldo deve ser um valor positivo")
		BigDecimal saldo,
		
		@NotNull(message = "O preenchimento do campo Banco é obrigatório")
		BancoEnum banco,
		
		AccountOwnerVO usuario,
		ZonedDateTime dataCriacao		
		
		) {

}
