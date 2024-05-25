package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.BancoEnum;
import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.vo.DominioVO;
import com.agsp.vo.factory.BancoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BancoService {

	public List<DominioVO> recuperarBancos(TipoBancoEnum tipo) {
		return TipoBancoEnum.TRADICIONAL.equals(tipo) ? getTipoTradicional() : getTipoDigital();
	}

	private List<DominioVO> getTipoDigital() {
		return BancoVOFactory.conveterListParaVO(BancoEnum.getDigital());
	}

	private List<DominioVO> getTipoTradicional() {
		return BancoVOFactory.conveterListParaVO(BancoEnum.getTradicional());
	}

}
