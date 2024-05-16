package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.CategoriaEnum;
import com.agsp.vo.CategoriaVO;

public class CategoriaVOFactory {
	
	private CategoriaVOFactory() {
		
	}
	
	public static CategoriaVO converterParaVO(CategoriaEnum categoria) {
		
		
		if(categoria != null) {
			return CategoriaVO.builder()
					.id(categoria.getId())
					.codigo(categoria.name())
					.descricao(categoria.getDescricao())
					.build();
		} else 
			return null;
	}
	

	public static List<CategoriaVO> converterListParaVO(List<CategoriaEnum> categorias) {
		return categorias.stream().map(CategoriaVOFactory::converterParaVO).toList();
	}

}
