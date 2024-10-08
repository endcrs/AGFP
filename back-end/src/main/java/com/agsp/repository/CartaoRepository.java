package com.agsp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agsp.entity.CartaoCreditoEntity;

public interface CartaoRepository extends JpaRepository<CartaoCreditoEntity, Long> {

	List<CartaoCreditoEntity> findByUsuarioCpf(String cpf);

	boolean existsByNumero(String numero);

	Optional<CartaoCreditoEntity> findByNumero(String numeroCartao);

//	@Query(value = "SELECT SUM(c.SALDO_DISPONIVEL) from TB_AGFP_CARTAO c "
//			+ "JOIN TB_AGFP_USUARIO u on c.ID_USUARIO_PROPRIETARIO = u.IDENT "
//			+ "WHERE u.IDENT = :idUsuario ", nativeQuery = true)
//	BigDecimal findSaldoTotalCartoesUsuario(Long idUsuario);

}
