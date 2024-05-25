package com.agsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agsp.entity.TransacaoEntity;

public interface TransacaoRespository extends JpaRepository<TransacaoEntity, Long> {

	@Query(value = "select t from TransacaoEntity t "
			+ "join t.cartao c "
			+ "join c.usuario u "
			+ "where u.cpf = :cpf ")
	List<TransacaoEntity> getAllTransacoesUsuario(@Param(value = "cpf") String cpf);

}
