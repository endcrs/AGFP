package com.agsp.enumerator;

public enum TipoBancoEnum {
	
	TRADICIONAL(10, "Banco Tradicional"),
	DIGITAL(20, "Banco Digital");
	
	private int id;
	private String descricao;
	
	
	private TipoBancoEnum(int id, String descricao) {
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
