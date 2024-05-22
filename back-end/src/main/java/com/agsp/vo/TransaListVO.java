package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String titulo;
	private BigDecimal valor;
	private DominioVO categoria;
	private LocalDate dataTransacao;
	private DominioVO tipoTranscao;
	

}
