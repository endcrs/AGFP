package com.agsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agsp.entity.InstallmentEntity;

@Repository
public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {

}
