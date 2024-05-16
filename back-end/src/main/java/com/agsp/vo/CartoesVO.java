package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.agsp.enumerator.BancoEnum;
import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.enumerator.TipoBandeiraEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartoesVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String numero;
	private BigDecimal limite;
	private String cvv;
	private String vencimento;
	private BancoEnum banco;
	private TipoBandeiraEnum bandeira;
	private TipoBancoEnum tipoBanco;

}
