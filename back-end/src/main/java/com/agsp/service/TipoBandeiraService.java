package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.TipoBandeiraEnum;
import com.agsp.vo.DominioVO;
import com.agsp.vo.factory.TipoBandeiraVOFactory;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TipoBandeiraService {
	
	public List<DominioVO> recuperarTiposBandeiras() {
		return TipoBandeiraVOFactory.converterListParaVO(TipoBandeiraEnum.getTiposBandeira());
	}

}
