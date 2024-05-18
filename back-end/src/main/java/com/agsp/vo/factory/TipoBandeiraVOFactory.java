package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.TipoBandeiraEnum;
import com.agsp.vo.TipoBandeiraVO;

public class TipoBandeiraVOFactory {
	
	private TipoBandeiraVOFactory() {}
	
	
	public static TipoBandeiraVO converterParaVO(TipoBandeiraEnum tiposBandeira) {
		
		if(tiposBandeira != null) {
			return TipoBandeiraVO.builder()
					.id(tiposBandeira.getId())
					.codigo(tiposBandeira.name())
					.descricao(tiposBandeira.getDescricao())
					.build();
		} else 
			return null;
	}


	public static List<TipoBandeiraVO> converterListParaVO(List<TipoBandeiraEnum> tiposBandeira) {
		return tiposBandeira.stream().map(TipoBandeiraVOFactory::converterParaVO).toList();
	}

}
