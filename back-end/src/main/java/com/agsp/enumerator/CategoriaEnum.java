package com.agsp.enumerator;

import java.util.Arrays;
import java.util.List;

public enum CategoriaEnum {
	
	
	ALIMENTACAO(504, "Alimentação"),
	BELEZA_ESTETICA(502, "Beleza"),
	ESPORTE_LAZER(503, "Esporte e Lazer"),
	SAUDE(501, "Saúde"),
	TRANSPORTE(500, "Transporte");
	
	
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
				CategoriaEnum.SAUDE,
				CategoriaEnum.TRANSPORTE
				);
	}


}
