package com.agsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agsp.entity.CreditCardTansationEntity;

@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTansationEntity, Long> {

}
