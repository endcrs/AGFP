package com.agsp.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoBancoVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codigo;
	private String descricao;

}
