package com.agsp.enumerator;

import java.util.Arrays;
import java.util.List;

public enum TipoBandeiraEnum {
	
	
	MASTERCARD(100, "Mastercard"),
	VISA(200, "Visa"),
	ELO(300, "Elo"),
	AMERICAN_EXPRESS(400, "American Express"),
	HIPERCARD(500, "Hipercard"),
	DINERS_CLUB(600, "Diners Club"),
	DISCOVER(700, "Discover"),;
	
	private int id;
	private String descricao;
	
	
	
	public static List<TipoBandeiraEnum> getTiposBandeira(){
		return Arrays.asList(TipoBandeiraEnum.MASTERCARD,
				TipoBandeiraEnum.VISA, 
				TipoBandeiraEnum.ELO, 
				TipoBandeiraEnum.AMERICAN_EXPRESS, 
				TipoBandeiraEnum.HIPERCARD,
				TipoBandeiraEnum.DINERS_CLUB,
				TipoBandeiraEnum.DISCOVER);
	}
	
	private TipoBandeiraEnum(int id, String descricao) {
		this.descricao = descricao;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
