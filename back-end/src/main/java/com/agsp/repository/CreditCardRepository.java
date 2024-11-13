package com.agsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agsp.entity.CreditCardEntity;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

//	List<CartaoCreditoEntity> findByUsuarioCpf(String cpf);

	boolean existsByNumero(String numero);

	
	@Query(value = "select card from CreditCardEntity card "
			+ "join card.account acount "
			+ "join account.usuario user "
			+ "where user.id = :usuarioId ")
	List<CreditCardEntity> findCardByUserId(Long usuarioId);

//	Optional<CartaoCreditoEntity> findByNumero(String numeroCartao);

//	@Query(value = "SELECT SUM(c.SALDO_DISPONIVEL) from TB_AGFP_CARTAO c "
//			+ "JOIN TB_AGFP_USUARIO u on c.ID_USUARIO_PROPRIETARIO = u.IDENT "
//			+ "WHERE u.IDENT = :idUsuario ", nativeQuery = true)
//	BigDecimal findSaldoTotalCartoesUsuario(Long idUsuario);

}
