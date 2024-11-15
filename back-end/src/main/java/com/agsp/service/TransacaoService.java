package com.agsp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agsp.entity.TransationEntity;
import com.agsp.enumerator.StatusEnum;
import com.agsp.exception.MsgException;
import com.agsp.repository.TransactionRepository;
import com.agsp.vo.TransaListVO;
import com.agsp.vo.TransacaoVO;
import com.agsp.vo.TransactionCurrentAccountResponseVO;
import com.agsp.vo.factory.TransactionCurrentAccountVOFcatory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransacaoService {
	
	private final TransactionRepository transactionRepository;
//	private final CartaoCreditoService cartaoService;

	public TransacaoVO salvar(TransacaoVO vo) {
		
//		CartaoCreditoEntity cartao = cartaoService.recuperarPorNumero(vo.getNumeroCartao());
		
//		validarValorTransacaoDespesa(cartao, vo);
		
//		TransacaoEntity transacao = TransacaoEntityFactory.converterParaEntity(cartao, vo);
		
//		cartao.setSaldoDisponivel(TipoTransacaoEnum.isDespesa(vo.getTipoTransacao())
//				? cartao.getSaldoDisponivel().subtract(vo.getValor())
//						: cartao.getSaldoDisponivel().add(vo.getValor()));
//		transacao = transacaoRespository.save(transacao);
//		return TransacaoVOFactory.converterParaVO(transacao);
		return TransacaoVO.builder().build();
	}

	public List<TransaListVO> listarTodasTransacoes(String cpf) {
		
		validarCpfUsuario(cpf);
		
//		List<TransacaoEntity> transacoes = transacaoRespository.getAllTransacoesUsuario(cpf);
//		
//		return transacoes.stream().map(TransacaoVOFactory::converterParaVOList).toList();
		return new ArrayList<>();
	}
	
	
	public List<TransaListVO> listarTransacoesMensais(String cpf) {
		
		validarCpfUsuario(cpf);
		
//		LocalDate dataFim = LocalDate.now(ZoneId.of(AMERICA_SAO_PAULO));
		
//		LocalDate dataInicio = dataFim.with(TemporalAdjusters.firstDayOfMonth());
		
//		List<TransacaoEntity> transacoes = transacaoRespository
//				.getTransacoesMensaisUsuario(cpf, dataInicio, dataFim);
//		
//		return transacoes.stream().map(TransacaoVOFactory::converterParaVOList).toList();
		return new ArrayList<>();
	}
	

	private void validarCpfUsuario(String cpf) {
		
		if(cpf.trim().length() != 11) {
			throw new MsgException("CPF invalido");
		}
	}

//	private void validarValorTransacaoDespesa(CartaoCreditoEntity cartao, TransacaoVO vo) {
		
//		if(TipoTransacaoEnum.isDespesa(vo.getTipoTransacao()) &&
//				(vo.getValor().longValue() > cartao.getSaldoDisponivel().longValue())) {
//				throw new MsgException("Valor da compra maior que saldo disponivel no cartao");
//		} 
//	}

//	public CategoriaListVO listarPercentagemGastoCategoria(String cpf) {
//		
//		validarCpfUsuario(cpf);
//		
////		List<TransacaoEntity> transacoes = transacaoRespository.getAllTransacoesUsuario(cpf);]
//		List<TransationEntity> transacoes = new ArrayList<>();
//		
//		Double alimentacao = 0.0;
//		Double belezaEstetica = 0.0;
//		Double esporteLazer = 0.0;
//		Double saude = 0.0;
//		Double transporte = 0.0;
//		Double educacao = 0.0;
//		
//		if( !transacoes.isEmpty()) {
//			
//			for(TransationEntity unidade : transacoes ) {
//				if(CategoriaEnum.ALIMENTACAO.equals(unidade.getCategoria())) {
//					alimentacao += 1;
//				}else if(CategoriaEnum.BELEZA_ESTETICA.equals(unidade.getCategoria())) {
//					belezaEstetica += 1;
//				}else if(CategoriaEnum.ESPORTE_LAZER.equals(unidade.getCategoria())) {
//					esporteLazer += 1;
//				}else if(CategoriaEnum.EDUCACAO.equals(unidade.getCategoria())) {
//					educacao += 1;
//				}else if(CategoriaEnum.SAUDE.equals(unidade.getCategoria())) {
//					saude += 1;
//				}else if(CategoriaEnum.TRANSPORTE.equals(unidade.getCategoria())) {
//					transporte += 1;
//				}
//			}
//		}
//		return setPercentagemByCategoria(alimentacao, belezaEstetica, esporteLazer, saude, transporte, educacao,
//				transacoes.isEmpty() ? 0 : transacoes.size() );
//	}

//	private CategoriaListVO setPercentagemByCategoria(Double valorAlimentacao, Double valorBelezaEstetica,
//			Double valorEsporteLazer, Double valorSaude, Double valorTransporte, Double valorEducacao, Integer totalTransacoes) {
//		
//		CategoriaVO alimentacao =  CategoriaVO.builder()
//				.percentagem(calcularPercentagem(valorAlimentacao, totalTransacoes)).build();
//		
//		CategoriaVO belezaEstetica =  CategoriaVO.builder()
//				.percentagem(calcularPercentagem(valorBelezaEstetica, totalTransacoes)).build();
//		
//		CategoriaVO esporteLazer =  CategoriaVO.builder()
//				.percentagem(calcularPercentagem(valorEsporteLazer, totalTransacoes)).build();
//		
//		CategoriaVO saude =  CategoriaVO.builder()
//				.percentagem(calcularPercentagem(valorSaude, totalTransacoes)).build();
//		
//		CategoriaVO transporte =  CategoriaVO.builder()
//				.percentagem(calcularPercentagem(valorTransporte, totalTransacoes)).build();
//		
//		CategoriaVO educacao =  CategoriaVO.builder()
//				.percentagem(calcularPercentagem(valorEducacao, totalTransacoes)).build();
//		
//		return CategoriaListVO.builder()
//				.alimentacao(alimentacao)
//				.belezaEstetica(belezaEstetica)
//				.esporteLazer(esporteLazer)
//				.saude(saude)
//				.transporte(transporte)
//				.educacao(educacao)
//				.build();
//	}
//
//	private BigDecimal calcularPercentagem(Double valor, Integer total) {
//		try {
//			if((valor != null && total != null)) {
//				return BigDecimal.valueOf((valor * 100) / total);
//			}
//		} catch (Exception e) {}
//		return  BigDecimal.valueOf(0.0);
//	}

	@Transactional
	public void disableTransaction(Long transactionId) {
		TransationEntity transaction = transactionRepository.findById(transactionId).get();
		
		if(Boolean.FALSE.equals(transaction.getAtivo())){
			throw new MsgException("Transação já foi desativado");
		}
		
		//implemetar para cartao de credito, desativar transacao cartao credito e parcelas
		transaction.setAtivo(Boolean.FALSE);
		transaction.setStatus(StatusEnum.NAO_ATIVO);
		transactionRepository.save(transaction);
	}

	public List<TransactionCurrentAccountResponseVO> getAllTransactions(Long usuarioId) {
			
		List<TransationEntity> transactions = transactionRepository.findAllTransactionByUserId(usuarioId);

		List<TransactionCurrentAccountResponseVO> vos = new ArrayList<>();
		transactions.forEach(t -> {
			vos.add(TransactionCurrentAccountVOFcatory.convertToVO(t));
		});

		return vos;
	}
	
	
}
