package com.agsp.vo;

import java.util.List;

import com.agsp.enumerator.BancoEnum;

public class BancoVOFactory {
	
	private BancoVOFactory() {}
	
	public static BancoVO converterParaVO(BancoEnum banco) {
		if(banco != null) {
			return BancoVO.builder()
					.id(banco.getId())
					.codigo(banco.name())
					.descricao(banco.getDescricao())
					.build();
		} else 
			return null;
	}
	

	public static List<BancoVO> conveterListParaVO(List<BancoEnum> devenvolvidores) {
		return devenvolvidores.stream().map(BancoVOFactory::converterParaVO).toList();
	}


}
