package com.agsp.vo;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Builder;

@Builder
public record AccountResponseVO(
		
		Long id,
		BigDecimal saldo,
		DominioVO banco,
		AccountOwnerVO usuario,
		ZonedDateTime dataCriacao
		
		
		
		) {

}
