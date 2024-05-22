package com.agsp.vo.factory;

import java.util.List;

import com.agsp.enumerator.CategoriaEnum;
import com.agsp.vo.DominioVO;

public class CategoriaVOFactory {
	
	private CategoriaVOFactory() {
		
	}
	
	public static DominioVO converterParaVO(CategoriaEnum categoria) {
		
		
		if(categoria != null) {
			return DominioVO.builder()
					.id(categoria.getId())
					.codigo(categoria.name())
					.descricao(categoria.getDescricao())
					.build();
		} else 
			return null;
	}
	

	public static List<DominioVO> converterListParaVO(List<CategoriaEnum> categorias) {
		return categorias.stream().map(CategoriaVOFactory::converterParaVO).toList();
	}

}
