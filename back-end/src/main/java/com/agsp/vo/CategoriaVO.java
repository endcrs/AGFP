package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Builder.Default
	private BigDecimal percentagem = BigDecimal.valueOf(0);
	

}
