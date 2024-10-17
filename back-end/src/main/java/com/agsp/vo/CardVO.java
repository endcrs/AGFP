package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.agsp.enumerator.TipoBandeiraEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private BigDecimal limite;
	private BigDecimal facturaAtual;
	private LocalDate fechamento;
	private Integer vencimento;
	private String validade;
	private TipoBandeiraEnum bandeira;
	private boolean status;
	

}
