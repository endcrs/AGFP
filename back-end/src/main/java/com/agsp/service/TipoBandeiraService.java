package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.TipoBandeiraEnum;
import com.agsp.vo.TipoBandeiraVO;
import com.agsp.vo.factory.TipoBandeiraVOFactory;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TipoBandeiraService {
	
	public List<TipoBandeiraVO> recuperarTiposBandeiras() {
		
		return TipoBandeiraVOFactory.converterListParaVO(TipoBandeiraEnum.getTiposBandeira());
	}

}
