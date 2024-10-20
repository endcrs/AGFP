package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.BancoEnum;
import com.agsp.vo.DominioVO;

public class BancoVOFactory {
	
	private BancoVOFactory() {}
	
	public static DominioVO converterParaVO(BancoEnum banco) {
		if(banco != null) {
			return DominioVO.builder()
					.id(banco.getId())
					.codigo(banco.name())
					.descricao(banco.getDescricao())
					.build();
		} else 
			return null;
	}
	

	public static List<DominioVO> converterListParaVO(List<BancoEnum> bancos) {
		return bancos.stream().map(BancoVOFactory::converterParaVO).toList();
	}


}
