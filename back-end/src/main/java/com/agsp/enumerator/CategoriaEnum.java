package com.agsp.enumerator;

import java.util.Arrays;
import java.util.List;

public enum CategoriaEnum {
	
	
	ALIMENTACAO(501, "Alimentação"),
	BELEZA_ESTETICA(502, "Beleza e Estética"),
	ESPORTE_LAZER(503, "Esporte e Lazer"),
	EDUCACAO(504, "Esporte e Lazer"),
	SAUDE(505, "Saúde"),
	TRANSPORTE(506, "Transporte");
	
	
	private int id;
	private String descricao;
	
	private CategoriaEnum(int id, String descricao) {
		this.descricao = descricao;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static List<CategoriaEnum> getCategorias(){
		return Arrays.asList(CategoriaEnum.ALIMENTACAO,
				CategoriaEnum.BELEZA_ESTETICA,
				CategoriaEnum.ESPORTE_LAZER,
				CategoriaEnum.EDUCACAO,
				CategoriaEnum.SAUDE,
				CategoriaEnum.TRANSPORTE
				);
	}


}
