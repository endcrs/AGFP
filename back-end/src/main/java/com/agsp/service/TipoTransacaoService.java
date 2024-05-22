package com.agsp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.vo.DominioVO;
import com.agsp.vo.factory.TipoTransacaoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoTransacaoService {
	
	public List<DominioVO> recuperarTiposTransacao() {
		return TipoTransacaoVOFactory.converterListParaVO(Arrays.asList(TipoTransacaoEnum.DESPESA, TipoTransacaoEnum.RECEITA));
	}

}
