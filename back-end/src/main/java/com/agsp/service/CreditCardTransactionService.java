package com.agsp.service;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.entity.CreditCardEntity;
import com.agsp.entity.CreditCardTansationEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.entity.factory.TransacaoEntityFactory;
import com.agsp.enumerator.CategoriaEnum;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.exception.MsgException;
import com.agsp.repository.CreditCardRepository;
import com.agsp.repository.CreditCardTransactionRepository;
import com.agsp.repository.TransactionRepository;
import com.agsp.vo.CategoriaListVO;
import com.agsp.vo.CategoriaVO;
import com.agsp.vo.TransactionCreditCardResponseVO;
import com.agsp.vo.TransactionCreditCardVO;
import com.agsp.vo.TransactionCurrentAccountVO;
import com.agsp.vo.factory.TransactionCreditCardVOFcatory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardTransactionService {
	
	
//	private final InstallmentRepository installmentRepository;
	private final TransactionRepository transactionRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardTransactionRepository creditCardTransactionRepository; 
	
	@Transactional
	public TransactionCurrentAccountVO createTransaction(TransactionCreditCardVO transaction) {
		
//		validateNumberOfInstallment(transaction.getNumeroParcelas());
		
		CreditCardEntity card = creditCardRepository.findById(transaction.getIdCartao()).get();
		
		validateCreditCardBalanse(card.getLimite().doubleValue(), transaction.getValor().doubleValue());
		
		TransationEntity tSave = TransacaoEntityFactory.convertTransactionToEntity(null, transaction, card.getAccount()); 
				
		tSave = transactionRepository.save(tSave);
		
		CreditCardTansationEntity tCreditCard = creditCardTransactionRepository.save(
				CreditCardTansationEntity.builder()
//				.numeroParcelas(transaction.getNumeroParcelas())
				.creditCard(card)
				.transation(tSave)
				.build());
		
//		if(isAvista(transaction.getNumeroParcelas())) {
//			installmentRepository.save(InstallmentEntity.builder()
//					.dataVencimento(tCreditCard.getCreditCard().getFechamento().plusDays(5)) //pensar sobre isso
//					.numeroParcelas(1)
//					.status(StatusEnum.ATIVO)
//					.valorParcela(transaction.getValor())
//					.creditCardTransation(tCreditCard)
//					.build());
//		} else {
//			registerInstallments(tCreditCard, transaction);
//		}
		
		debitInCreditCardLimit(transaction, card);
		
		return TransactionCurrentAccountVO.builder()
				.id(tCreditCard.getTransation().getId())
				.valor(tCreditCard.getTransation().getValorCompra())
				.build();
	}
	
//	private void validateNumberOfInstallment(Integer numeroParcelas) {
//		
//		if(numeroParcelas > 12) {
//			throw new MsgException("Número de parcelas maior que permitido");
//		}
//	}

//	private void registerInstallments(CreditCardTansationEntity tCreditCard, TransactionCreditCardVO transaction) {
//			
//		for (int i = 0; i < transaction.getNumeroParcelas(); i++) {
//			
//			installmentRepository.save(InstallmentEntity.builder()
//					.dataVencimento(i == 0 ? tCreditCard.getCreditCard().getFechamento()
//							: getDataVencimentoPeloNumerosParcelas(i, tCreditCard.getCreditCard().getFechamento())) //definir
//					.numeroParcelas(transaction.getNumeroParcelas())
//					.status(StatusEnum.ATIVO)
//					.valorParcela(transaction.getValor().divide(new BigDecimal(transaction.getNumeroParcelas()), 10, RoundingMode.HALF_UP))
//					.creditCardTransation(tCreditCard)
//					.build());
//		}
//	}

//	private LocalDate getDataVencimentoPeloNumerosParcelas(Integer iterator, LocalDate dataFechamento) {
//		
//		switch (iterator) {
//		case 1:
//			return dataFechamento.plusDays(30); 
//		case 2:
//			return dataFechamento.plusDays(60);
//		case 3:
//			return dataFechamento.plusDays(90);
//		case 4:
//			return dataFechamento.plusDays(120);
//		case 5:
//			return dataFechamento.plusDays(150);
//		case 6:
//			return dataFechamento.plusDays(180);
//		case 7:
//			return dataFechamento.plusDays(210);
//		case 8:
//			return dataFechamento.plusDays(240);
//		case 9:
//			return dataFechamento.plusDays(270);
//		case 10:
//			return dataFechamento.plusDays(300);
//		case 11:
//			return dataFechamento.plusDays(330);
//		case 12:
//			return dataFechamento.plusDays(360);
//		default:
//			break;
//		}
//		return null;
//	}

//	private ZonedDateTime getZoneDateTime() {
//		return ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
//	}

//	private boolean isAvista(Integer numeroParcelas) {
//		return numeroParcelas == 1;
//	}

	private void validateCreditCardBalanse(double creditCardLimit, double transactionValue) {
		if(transactionValue > creditCardLimit) {
			throw new MsgException("Não foi possivel efectuar a compra, valor da compra maior que limite disponivel");
		}
	}
	
	private void debitInCreditCardLimit(TransactionCreditCardVO transaction, CreditCardEntity card) {
		card.setLimite(card.getLimite().subtract(transaction.getValor()));
		card.setFacturaAtual(card.getFacturaAtual().add(transaction.getValor())); 
		creditCardRepository.save(card);
	}

	public List<TransactionCreditCardResponseVO> getTransactions(Long userId) {
		
		List<CreditCardTansationEntity> transactions = creditCardTransactionRepository
				.findByCreditCardIdAndTipoAndStatus(userId, StatusEnum.ATIVO);
		
		List<TransactionCreditCardResponseVO> vos = new ArrayList<>();
		transactions.forEach(t -> {
			vos.add(TransactionCreditCardVOFcatory.convertToVO(t));
		});
		
		return vos;
		
	}

	public List<TransactionCreditCardResponseVO> getMensalTransactions(Long userId) {
		
		ZonedDateTime dataFim = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
		
		ZonedDateTime dataInicio = dataFim.withDayOfMonth(1);
		
		List<CreditCardTansationEntity> transactions = creditCardTransactionRepository
				.findMensalTransactionByCreditCardIdAndTipoAndStatus
				(userId, TipoTransacaoEnum.DESPESA, StatusEnum.ATIVO, dataInicio, dataFim);
		
		List<TransactionCreditCardResponseVO> vos = new ArrayList<>();
		transactions.forEach(t -> {
			vos.add(TransactionCreditCardVOFcatory.convertToVO(t));
		});
		
		return vos;
		
	}
	
	public List<TransactionCreditCardResponseVO> getTransactionsByCreditCard(Long cardId) {
		
		List<CreditCardTansationEntity> transactions = creditCardTransactionRepository
				.findByCreditCardTransactionById(cardId, StatusEnum.ATIVO);
		
		List<TransactionCreditCardResponseVO> vos = new ArrayList<>();
		transactions.forEach(t -> {
			vos.add(TransactionCreditCardVOFcatory.convertToVO(t));
		});
		
		return vos;
	}

	public CategoriaListVO listPercentageSpentByCategory(Long userId) {
		
		List<TransationEntity> transacoes = creditCardTransactionRepository
				.getAllTransactionsCreditCard(userId, TipoTransacaoEnum.DESPESA, StatusEnum.ATIVO);
		
		Double alimentacao = 0.0;
		Double belezaEstetica = 0.0;
		Double esporteLazer = 0.0;
		Double saude = 0.0;
		Double transporte = 0.0;
		Double educacao = 0.0;
		
		if( !transacoes.isEmpty()) {
			
			for(TransationEntity unidade : transacoes ) {
				if(CategoriaEnum.ALIMENTACAO.equals(unidade.getCategoria())) {
					alimentacao += 1;
				}else if(CategoriaEnum.BELEZA_ESTETICA.equals(unidade.getCategoria())) {
					belezaEstetica += 1;
				}else if(CategoriaEnum.ESPORTE_LAZER.equals(unidade.getCategoria())) {
					esporteLazer += 1;
				}else if(CategoriaEnum.EDUCACAO.equals(unidade.getCategoria())) {
					educacao += 1;
				}else if(CategoriaEnum.SAUDE.equals(unidade.getCategoria())) {
					saude += 1;
				}else if(CategoriaEnum.TRANSPORTE.equals(unidade.getCategoria())) {
					transporte += 1;
				}
			}
		}
		return setPercentagemByCategoria(alimentacao, belezaEstetica, esporteLazer, saude, transporte, educacao,
				transacoes.isEmpty() ? 0 : transacoes.size() );
	}
	
	private CategoriaListVO setPercentagemByCategoria(Double valorAlimentacao, Double valorBelezaEstetica,
			Double valorEsporteLazer, Double valorSaude, Double valorTransporte, Double valorEducacao,
			Integer totalTransacoes) {

		CategoriaVO alimentacao = CategoriaVO.builder()
				.percentagem(calcularPercentagem(valorAlimentacao, totalTransacoes)).build();

		CategoriaVO belezaEstetica = CategoriaVO.builder()
				.percentagem(calcularPercentagem(valorBelezaEstetica, totalTransacoes)).build();

		CategoriaVO esporteLazer = CategoriaVO.builder()
				.percentagem(calcularPercentagem(valorEsporteLazer, totalTransacoes)).build();

		CategoriaVO saude = CategoriaVO.builder().percentagem(calcularPercentagem(valorSaude, totalTransacoes)).build();

		CategoriaVO transporte = CategoriaVO.builder()
				.percentagem(calcularPercentagem(valorTransporte, totalTransacoes)).build();

		CategoriaVO educacao = CategoriaVO.builder().percentagem(calcularPercentagem(valorEducacao, totalTransacoes))
				.build();

		return CategoriaListVO.builder().alimentacao(alimentacao).belezaEstetica(belezaEstetica)
				.esporteLazer(esporteLazer).saude(saude).transporte(transporte).educacao(educacao).build();
	}

	private BigDecimal calcularPercentagem(Double valor, Integer total) {
		try {
			if ((valor != null && total != null)) {
				return BigDecimal.valueOf((valor * 100) / total);
			}
		} catch (Exception e) {
		}
		return BigDecimal.valueOf(0.0);
	}

}
