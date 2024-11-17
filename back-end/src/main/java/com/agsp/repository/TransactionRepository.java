package com.agsp.repository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agsp.entity.TransationEntity;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;

public interface TransactionRepository extends JpaRepository<TransationEntity, Long> {

	@Query(value = "select t.id from TransationEntity t "
	+ "join t.currentAccount c "
	+ "where c.id = :accountId ")
	List<Long> hasTransationCurrentAccount(Long accountId);

	@Query(value = "select t from TransationEntity t "
			+ "join t.currentAccount c "
			+ "join c.usuario user "
			+ "where user.id = :userId and t.status =:ativo ")
	List<TransationEntity> findByCurrentAccountIdAndTipoAndStatus(@Param("userId") Long userId, StatusEnum ativo);
	
	@Query(value = "select t from TransationEntity t "
			+ "join t.currentAccount c "
			+ "join c.usuario user "
			+ "where user.id = :userId ")
	List<TransationEntity> findAllTransactionByUserId(@Param(value = "userId") Long userId);
	
	@Query(value = "select t from TransationEntity t "
			+ "join t.currentAccount c "
			+ "join c.usuario user "
			+ "where c.id = :accountId and t.dataTransacao between :startDate and :endDate and t.tipo =:despesa and t.status =:ativo")
	List<TransationEntity> findMensalTransactionByCurrentAccountIdAndTipoAndStatus(
			Long accountId, TipoTransacaoEnum despesa, StatusEnum ativo, ZonedDateTime startDate, ZonedDateTime endDate);
	
	@Query(value = "select t from TransationEntity t "
			+ "join t.currentAccount c "
			+ "join c.usuario u "
			+ "where u.id = :userId and t.tipo= :despesa and t.transacao ='CONTA' and t.status =:ativo ")
	List<TransationEntity> getAllTransactionsCurrentAccount(Long userId, TipoTransacaoEnum despesa, StatusEnum ativo);
	
//	@Query(value = "select t from TransacaoEntity t "
//			+ "join t.cartao c "
//			+ "join c.usuario u "
//			+ "where u.cpf = :cpf ")
//	List<TransacaoEntity> getAllTransacoesUsuario(@Param(value = "cpf") String cpf);
//
//	@Query(value = "select t from TransacaoEntity t "
//			+ "join t.cartao c "
//			+ "join c.usuario u "
//			+ "where u.cpf = :cpf and t.dataTransacao between :startDate and :endDate")
//	List<TransacaoEntity> getTransacoesMensaisUsuario(@Param(value = "cpf") String cpf, 
//			@Param(value = "startDate")LocalDate startDate, 
//			@Param(value = "endDate")LocalDate endDate);
//
//	@Query(value = "select sum(t.valorCompra) from TransacaoEntity t "
//			+ "join t.cartao c "
//			+ "join c.usuario u "
//			+ "where u.cpf = :cpf and t.dataTransacao between :startDate "
//			+ "and :endDate and t.tipoTransacao = :tipoTransacao ")
//	BigDecimal getTotalReceitaOrDespesaMensal(
//			@Param(value = "cpf") String cpf, 
//			@Param(value = "startDate")LocalDate startDate, 
//			@Param(value = "endDate")LocalDate endDate,
//			@Param(value = "tipoTransacao") TipoTransacaoEnum tipoTransacao);
	
	@Query(value = "select sum(t.valorCompra) from TransationEntity t "
			+ "join t.currentAccount c "
			+ "join c.usuario u "
			+ "where u.id = :usuarioId and t.dataTransacao between :startDate "
			+ "and :endDate and t.tipo = :tipoTransacao and t.transacao ='CONTA' and t.status=:ativo ")
	BigDecimal getTotalMonthlyExpensesOrRevenues(
			@Param(value = "usuarioId") Long usuarioId, 
			@Param(value = "startDate")ZonedDateTime startDate, 
			@Param(value = "endDate")ZonedDateTime endDate,
			@Param(value = "tipoTransacao") TipoTransacaoEnum tipoTransacao, 
			@Param(value = "ativo") StatusEnum ativo);

}
