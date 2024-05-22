package com.agsp.enumerator;

public enum TipoTransacaoEnum {
	
	RECEITA(700, "Receita"),
	DESPESA(701, "Despesa");
	
	private int id;
	private String descricao;
	
	private TipoTransacaoEnum(int id, String descricao) {
		this.descricao = descricao;
		this.id = id;
	}
	
	
	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static boolean isReceita(TipoTransacaoEnum tipoPagamento) {
		return tipoPagamento.equals(TipoTransacaoEnum.RECEITA);
	}
	
	public static boolean isDespesa(TipoTransacaoEnum tipoPagamento) {
		return tipoPagamento.equals(TipoTransacaoEnum.DESPESA);
	}
	
}
