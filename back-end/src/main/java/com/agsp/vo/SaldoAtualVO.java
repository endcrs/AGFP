package com.agsp.vo;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record SaldoAtualVO(
		
		BigDecimal saldoAtual,
		BigDecimal despesa,
		BigDecimal receita,
		BigDecimal lucro
		
		) {

}
