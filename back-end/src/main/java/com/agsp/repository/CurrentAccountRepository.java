package com.agsp.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agsp.entity.CurrentAccountEntity;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccountEntity, Long> {

	List<CurrentAccountEntity> findByUsuarioId(Long usuarioId);
	
	@Query(value = "select sum(c.saldo) from CurrentAccountEntity c "
			+ "join c.usuario u "
			+ "where u.id = :usuarioId ")
	BigDecimal getTotalSaldoMensal(@Param(value = "usuarioId") Long usuarioId);
}
