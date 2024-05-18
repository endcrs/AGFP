package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.CategoriaEnum;
import com.agsp.vo.CategoriaVO;
import com.agsp.vo.factory.CategoriaVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
	
	public List<CategoriaVO> recuperarCategorias() {
		return CategoriaVOFactory.converterListParaVO(CategoriaEnum.getCategorias()) ;
	}
	
}
