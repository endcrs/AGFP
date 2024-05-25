package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.TipoBandeiraEnum;
import com.agsp.vo.DominioVO;

public class TipoBandeiraVOFactory {
	
	private TipoBandeiraVOFactory() {}
	
	
	public static DominioVO converterParaVO(TipoBandeiraEnum tiposBandeira) {
		
		if(tiposBandeira != null) {
			return DominioVO.builder()
					.id(tiposBandeira.getId())
					.codigo(tiposBandeira.name())
					.descricao(tiposBandeira.getDescricao())
					.build();
		} else 
			return null;
	}


	public static List<DominioVO> converterListParaVO(List<TipoBandeiraEnum> tiposBandeira) {
		return tiposBandeira.stream().map(TipoBandeiraVOFactory::converterParaVO).toList();
	}

}
