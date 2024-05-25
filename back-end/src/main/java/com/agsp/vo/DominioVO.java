package com.agsp.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DominioVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String codigo;
	private String descricao;

}
