package com.agsp.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agsp.entity.CartaoEntity;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

	List<CartaoEntity> findByUsuarioCpf(String cpf);

	boolean existsByNumero(String numero);

	Optional<CartaoEntity> findByNumero(String numeroCartao);

	@Query(value = "SELECT SUM(c.SALDO_DISPONIVEL) from TB_AGFP_CARTAO c "
			+ "JOIN TB_AGFP_USUARIO u on c.ID_USUARIO_PROPRIETARIO = u.IDENT "
			+ "WHERE u.IDENT = :idUsuario ", nativeQuery = true)
	BigDecimal findSaldoTotalCartoesUsuario(Long idUsuario);

}
