package com.agsp.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CategoriaVO alimentacao;
	private CategoriaVO belezaEstetica;
	private CategoriaVO esporteLazer;
	private CategoriaVO educacao;
	private CategoriaVO saude;
	private CategoriaVO transporte;
	
}
