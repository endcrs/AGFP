package com.agsp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.vo.DominioVO;
import com.agsp.vo.factory.TipoBancoVOFactory;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TipoBancoService{
	
	
	public List<DominioVO> recuperarTipoConta() {
		return TipoBancoVOFactory.converterListParaVO(Arrays.asList(TipoBancoEnum.DIGITAL, TipoBancoEnum.TRADICIONAL));
	}

}
