package com.agsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agsp.entity.CurrentAccountEntity;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccountEntity, Long> {

	List<CurrentAccountEntity> findByUsuarioId(Long usuarioId);

}
