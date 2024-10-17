package com.agsp.vo.factory;

import java.util.List;

import com.agsp.entity.CreditCardEntity;
import com.agsp.vo.CardVO;
import com.agsp.vo.CreditCardVO;

public class CreditCardVOFactory {

	private CreditCardVOFactory() {
	}

	public static CreditCardVO converterParaVO(CreditCardEntity entity) {

		if (entity != null) {
			return CreditCardVO.builder().id(entity.getId())
//					.nome(entity.getNome())
					.numero(entity.getNumero())
//					.saldo(entity.getSaldoDisponivel())
//					.cvv(entity.getCvv())
//					.vencimento(entity.getVencimento())
//					.banco(entity.getBanco())
//					.bandeira(entity.getBandeira())
//					.tipoBanco(entity.getTipobanco())
					.build();
		} else
			return null;
	}

	public static CardVO converterParaVOCadastro(CreditCardEntity entity) {

		if (entity != null) {
			return CardVO.builder()
//					.id(entity.getId())
//					.nome(entity.getNome())
//					.numero(entity.getNumero())
//					.saldo(entity.getSaldoDisponivel())
//					.cvv(entity.getCvv())
//					.vencimento(entity.getVencimento())
//					.banco(entity.getBanco())
//					.bandeira(entity.getBandeira())
//					.tipoBanco(entity.getTipobanco())
					.build();
		} else
			return null;
	}

	public static List<CardVO> converterListParaVO(List<CreditCardEntity> cartoes) {

		return cartoes.stream().map(CreditCardVOFactory::converterParaVOCadastro).toList();
	}

	public static CardVO toVO(CreditCardEntity card) {

		if (card != null) {
			return CardVO.builder()
					.id(card.getId())
					.status(card.getAtivo())
					.bandeira(card.getBandeira())
					.facturaAtual(card.getFacturaAtual())
					.fechamento(card.getFechamento())
					.limite(card.getLimite())
					.numero(card.getNumero())
					.validade(card.getValidade())
					.vencimento(card.getVencimento())
					.build();
		}
		return null;

	}

}
