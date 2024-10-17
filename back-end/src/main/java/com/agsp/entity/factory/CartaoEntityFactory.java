package com.agsp.entity.factory;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import com.agsp.entity.CreditCardEntity;
import com.agsp.entity.CurrentAccountEntity;
import com.agsp.vo.CreditCardVO;

public class CartaoEntityFactory {
	
	private CartaoEntityFactory() {}

	public static CreditCardEntity converterParaEntity(CurrentAccountEntity account, CreditCardVO vo) {
		
		
		if(account != null && vo != null) {
			
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
			
			LocalDate fechamento = LocalDate.of(now.getYear(), now.getMonth(), vo.vencimento());
			
			return CreditCardEntity.builder()
					.numero(vo.numero())
					.limite(vo.limite())
					.validade(vo.validade())
					.bandeira(vo.bandeira())
					.facturaAtual(BigDecimal.ZERO)
					.vencimento(vo.vencimento()) // receber dia no parametro
					.fechamento(fechamento.minusDays(5)) //contruir dia apartir do dia venciemento menos 5 dias
					.account(account)
					.ativo(Boolean.TRUE)
					.dataCadastro(now)
					.transations(new ArrayList<>())
					.build();
		} else 
			return null;
	}

}
