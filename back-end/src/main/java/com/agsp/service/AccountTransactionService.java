package com.agsp.service;

import static com.agsp.util.Constantes.AMERICA_SAO_PAULO;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.agsp.entity.CurrentAccountEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.entity.factory.TransacaoEntityFactory;
import com.agsp.enumerator.CategoriaEnum;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.repository.CurrentAccountRepository;
import com.agsp.repository.TransactionRepository;
import com.agsp.vo.CategoriaListVO;
import com.agsp.vo.CategoriaVO;
import com.agsp.vo.TransactionCurrentAccountResponseVO;
import com.agsp.vo.TransactionCurrentAccountVO;
import com.agsp.vo.factory.TransactionCurrentAccountVOFcatory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {
	
	private final TransactionRepository transactionRepository;
	private final CurrentAccountRepository currentAccountRepository;
	
	@Transactional
	public TransactionCurrentAccountVO createTransaction(TransactionCurrentAccountVO transaction) {
		
		validateTransationType(transaction);
		
		CurrentAccountEntity account = currentAccountRepository.findById(transaction.getIdConta()).get();
		
		validateAccountBalanse(account.getSaldo().doubleValue(), transaction.getValor().doubleValue(), transaction.getTipo());
		
		TransationEntity save = TransacaoEntityFactory.convertTransactionToEntity(transaction, null, account); 
		
//		if(!hasTransationCurrentAccount(transaction)) {
//			save.setTipo(TipoTransacaoEnum.RECEITA);
//		}
		
		transactionRepository.save(save);
		
		debitOrAddInCurrentAccount(transaction, account);
		
		return TransactionCurrentAccountVO.builder()
				.id(save.getId())
				.valor(save.getValorCompra())
				.build();
	}
	
	@Transactional
	public TransactionCurrentAccountVO updateTransaction(TransactionCurrentAccountVO vo) {
		
		validateField(vo);
		
		TransationEntity transaction = getTransactionEntity(vo.getId());
		BigDecimal lastValue = transaction.getValorCompra();
		
		validateAccountBalanse(transaction.getCurrentAccount().getSaldo().doubleValue(), vo.getValor().doubleValue(), vo.getTipo());
		
		transaction.setCategoria(vo.getCategoria());
		transaction.setValorCompra(vo.getValor());
		transaction.setDataTransacao(ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)));
				
		transactionRepository.save(transaction);
		
		updateCurrentAccount(vo, transaction.getCurrentAccount(), lastValue);
		
		return TransactionCurrentAccountVO.builder()
				.id(transaction.getId())
				.valor(transaction.getValorCompra())
				.build();
	}

	private void updateCurrentAccount(TransactionCurrentAccountVO vo, CurrentAccountEntity currentAccount, BigDecimal lastValue) {
		BigDecimal currentValue =  currentAccount.getSaldo().add(lastValue);
		
		if(TipoTransacaoEnum.isDespesa(vo.getTipo())) {
			currentAccount.setSaldo(currentValue.subtract(vo.getValor()));
		} else {
			
			BigDecimal v = currentAccount.getSaldo().subtract(lastValue);
			currentAccount.setSaldo(v.add(vo.getValor()));
		}
		
		currentAccountRepository.save(currentAccount);
	}
	
	private void validateField(TransactionCurrentAccountVO transaction) {
		if(transaction.getId() == null) {
			throw new MsgException("O preenchimento do campo ID é obrigatório");
		} 
	}

	private void debitOrAddInCurrentAccount(TransactionCurrentAccountVO transaction, CurrentAccountEntity account) {
		account.setSaldo(TipoTransacaoEnum.isDespesa(transaction.getTipo()) ? 
				account.getSaldo().subtract(transaction.getValor()) 
						: account.getSaldo().add(transaction.getValor()));
		currentAccountRepository.save(account);
	}
	
	private TransationEntity getTransactionEntity(Long id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Transação não encontrada"));
	}
	
//	private boolean hasTransationCurrentAccount(TransactionCurrentAccountVO transaction) {
//		return transactionRepository.hasTransationCurrentAccount(transaction.getIdConta()).size() > 0;
//	}

	private void validateTransationType(TransactionCurrentAccountVO transaction) {
		if(Objects.isNull(transaction.getTipo())) {
			throw new MsgException("O preenchimento do campo tipo transação é obrigatório");
		}
	}

	private void validateAccountBalanse(double accountValue, double transactionValue, TipoTransacaoEnum transactioType) {
		if(TipoTransacaoEnum.isDespesa(transactioType)  && transactionValue > accountValue)
			throw new MsgException("Não foi possivel efectuar a compra valor da compra maior que saldo disponivel");
	}
	
	public List<TransactionCurrentAccountResponseVO> getTransactions(Long userId) {
		
		List<TransationEntity> transactions = transactionRepository
				.findByCurrentAccountIdAndTipoAndStatus(userId, StatusEnum.ATIVO);
		
		List<TransactionCurrentAccountResponseVO> vos = new ArrayList<>();
		transactions.forEach(t -> {
			vos.add(TransactionCurrentAccountVOFcatory.convertToVO(t));
		});
		
		return vos;
	}

	public List<TransactionCurrentAccountResponseVO> getMensalTransactions(Long accountId) {
		
		ZonedDateTime dataFim = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
		
		ZonedDateTime dataInicio = dataFim.withDayOfMonth(1);
		
		List<TransationEntity> transactions = transactionRepository
				.findMensalTransactionByCurrentAccountIdAndTipoAndStatus
				(accountId, TipoTransacaoEnum.DESPESA, StatusEnum.ATIVO, dataInicio, dataFim);
		
		List<TransactionCurrentAccountResponseVO> vos = new ArrayList<>();
		transactions.forEach(t -> {
			vos.add(TransactionCurrentAccountVOFcatory.convertToVO(t));
		});
		
		return vos;
	}

	public CategoriaListVO listPercentageSpentByCategory(Long userId) {
		
		List<TransationEntity> transacoes = transactionRepository.getAllTransactionsCurrentAccount(userId, TipoTransacaoEnum.DESPESA, StatusEnum.ATIVO);
			
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
