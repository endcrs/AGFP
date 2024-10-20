package com.agsp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.enumerator.BancoEnum;
import com.agsp.vo.DominioVO;
import com.agsp.vo.factory.BancoVOFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BancoService {

	public List<DominioVO> recuperarBancos() {
		return  BancoVOFactory.converterListParaVO(BancoEnum.getBancos());
	}

}
