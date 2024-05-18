package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.BancoEnum;
import com.agsp.enumerator.TipoBancoEnum;
import com.agsp.vo.BancoVO;
import com.agsp.vo.BancoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BancoService {

	public List<BancoVO> recuperarBancos(TipoBancoEnum tipo) {
		return TipoBancoEnum.TRADICIONAL.equals(tipo) ? getTipoTradicional() : getTipoDigital();
	}

	private List<BancoVO> getTipoDigital() {
		return BancoVOFactory.conveterListParaVO(BancoEnum.getDigital());
	}

	private List<BancoVO> getTipoTradicional() {
		return BancoVOFactory.conveterListParaVO(BancoEnum.getTradicional());
	}

}
