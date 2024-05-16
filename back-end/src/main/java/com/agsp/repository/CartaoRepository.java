package com.agsp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agsp.entity.CartaoEntity;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

	List<CartaoEntity> findByUsuarioCpf(String cpf);

	boolean existsByNumero(String numero);

	Optional<CartaoEntity> findByNumero(String numeroCartao);

}
