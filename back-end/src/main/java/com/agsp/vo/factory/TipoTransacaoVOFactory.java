package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.vo.DominioVO;

public class TipoTransacaoVOFactory {
	
	
	private TipoTransacaoVOFactory() {}

	
	public static DominioVO converteParaVO(TipoTransacaoEnum tipoPagamento) {
		
		if(tipoPagamento != null) {
			return DominioVO.builder()
					.id(tipoPagamento.getId())
					.codigo(tipoPagamento.name())
					.descricao(tipoPagamento.getDescricao())
					.build();
		} else 
			return null;
	}

	
	public static List<DominioVO> converterListParaVO(List<TipoTransacaoEnum> tiposBancos) {
		return tiposBancos.stream().map(TipoTransacaoVOFactory::converteParaVO).toList();
	}

}
