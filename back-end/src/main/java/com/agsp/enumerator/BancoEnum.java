package com.agsp.enumerator;

import java.util.Arrays;
import java.util.List;

public enum BancoEnum {
	
	
	NUBANK(260, "Nubank"),
	BANCO_INTER(77, "Banco Inter S.A."),
	PAGBANK(290, "PagBank"),
	C6_BANK(336, "C6 Bank"),
	STONE(197, "Stone Pagamentos"),
	NEON(735, "Neon Pagamentos"),
	NEXT(237, "Next"),
	MERCADO_PAGO(323, "Mercado Pago"),
	PICPAY(380, "PicPay"),
	BANCO_ORIGINAL(212, "Banco Original"),
	
	
	BANCO_BRASIL(1, "Banco do Brasil"),
	ITAU(341, "Itaú"),
	BRADESCO(237, "Banco Bradesco S.A."),
	CAIXA_ECONOMICA(104, "Caixa Econômica Federal"),
	SANTANDER(237, "Santander");
	
	private int id;
	private String descricao;
	
	
	public static List<BancoEnum> getTradicional() {
		return Arrays.asList(BancoEnum.BANCO_BRASIL, BancoEnum.ITAU, 
				BancoEnum.BRADESCO, BancoEnum.CAIXA_ECONOMICA, BancoEnum.SANTANDER);
	}
	
	public static List<BancoEnum> getDigital() {
		return Arrays.asList(BancoEnum.NUBANK, BancoEnum.BANCO_INTER, BancoEnum.PAGBANK, 
				BancoEnum.C6_BANK, BancoEnum.STONE, BancoEnum.NEON, BancoEnum.NEXT, 
				BancoEnum.MERCADO_PAGO, BancoEnum.PICPAY, BancoEnum.BANCO_ORIGINAL);
	}
	
	public static List<BancoEnum> getBancos() {
		return Arrays.asList(BancoEnum.BANCO_BRASIL, BancoEnum.ITAU, 
				BancoEnum.BRADESCO, BancoEnum.CAIXA_ECONOMICA, BancoEnum.SANTANDER,
				BancoEnum.NUBANK, BancoEnum.BANCO_INTER, BancoEnum.PAGBANK, 
				BancoEnum.C6_BANK, BancoEnum.STONE, BancoEnum.NEON, BancoEnum.NEXT, 
				BancoEnum.MERCADO_PAGO, BancoEnum.PICPAY, BancoEnum.BANCO_ORIGINAL);
	}
	
	private BancoEnum(int id, String descricao) {
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
