package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.vo.TipoBancoVO;

public class TipoBancoVOFactory {
	
	
	private TipoBancoVOFactory() {}

	
	public static TipoBancoVO converteParaVO(TipoBancoEnum tiposBanco) {
		
		if(tiposBanco != null) {
			return TipoBancoVO.builder()
					.id(tiposBanco.getId())
					.codigo(tiposBanco.name())
					.descricao(tiposBanco.getDescricao())
					.build();
		} else 
			return null;
	}

	
	public static List<TipoBancoVO> converterListParaVO(List<TipoBancoEnum> tiposBancos) {
		return tiposBancos.stream().map(TipoBancoVOFactory::converteParaVO).toList();
	}

}
