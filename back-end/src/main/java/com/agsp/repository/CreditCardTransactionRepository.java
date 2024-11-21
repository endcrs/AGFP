package com.agsp.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agsp.entity.CreditCardTansationEntity;
import com.agsp.entity.TransationEntity;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;

@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTansationEntity, Long> {
	
	@Query(value = "select tCard from CreditCardTansationEntity tCard "
			+ "join tCard.transation t "
			+ "join t.currentAccount c "
			+ "join c.usuario user "
			+ "where user.id = :userId and t.status =:ativo ")
	List<CreditCardTansationEntity> findByCreditCardIdAndTipoAndStatus(@Param("userId") Long userId, StatusEnum ativo);
	
	@Query(value = "select tCard from CreditCardTansationEntity tCard "
			+ "join tCard.transation t "
			+ "join t.currentAccount c "
			+ "join c.usuario user "
			+ "where user.id = :userId and t.dataTransacao between :startDate and :endDate and t.tipo =:despesa and t.status =:ativo")
	List<CreditCardTansationEntity> findMensalTransactionByCreditCardIdAndTipoAndStatus(
			Long userId, TipoTransacaoEnum despesa, StatusEnum ativo, ZonedDateTime startDate, ZonedDateTime endDate);
	
	@Query(value = "select t from CreditCardTansationEntity tCard "
			+ "join tCard.transation t "
			+ "join t.currentAccount c "
			+ "join c.usuario u "
			+ "where u.id = :userId and t.tipo= :despesa and t.transacao ='CARTAO' and t.status =:ativo ")
	List<TransationEntity> getAllTransactionsCreditCard(Long userId, TipoTransacaoEnum despesa, StatusEnum ativo);

}
