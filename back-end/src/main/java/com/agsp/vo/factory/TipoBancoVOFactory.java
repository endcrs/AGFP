package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.vo.DominioVO;

public class TipoBancoVOFactory {
	
	
	private TipoBancoVOFactory() {}

	
	public static DominioVO converteParaVO(TipoBancoEnum tiposBanco) {
		
		if(tiposBanco != null) {
			return DominioVO.builder()
					.id(tiposBanco.getId())
					.codigo(tiposBanco.name())
					.descricao(tiposBanco.getDescricao())
					.build();
		} else 
			return null;
	}

	
	public static List<DominioVO> converterListParaVO(List<TipoBancoEnum> tiposBancos) {
		return tiposBancos.stream().map(TipoBancoVOFactory::converteParaVO).toList();
	}

}
