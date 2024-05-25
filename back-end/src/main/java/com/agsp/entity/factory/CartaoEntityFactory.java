package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.agsp.entity.CartaoEntity;
import com.agsp.entity.UsuarioEntity;
import com.agsp.vo.CartaoVO;

public class CartaoEntityFactory {
	
	private CartaoEntityFactory() {}

	public static CartaoEntity converterParaEntity(UsuarioEntity usuario, CartaoVO vo) {
		
		if(usuario != null && vo != null) {
			return CartaoEntity.builder()
					.banco(vo.banco())
					.bandeira(vo.bandeira())
					.vencimento(vo.vencimento())
					.saldoDisponivel(vo.saldo())
					.dataHora(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)))
					.tipobanco(vo.tipoBanco())
					.cvv(vo.cvv())
					.nome(vo.nome())
					.numero(vo.numero())
					.usuario(usuario)
					.build();
		} else 
			return null;
	}

}
