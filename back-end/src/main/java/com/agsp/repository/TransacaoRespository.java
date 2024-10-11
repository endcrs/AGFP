package com.agsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agsp.entity.TransationEntity;

public interface TransacaoRespository extends JpaRepository<TransationEntity, Long> {

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
}
