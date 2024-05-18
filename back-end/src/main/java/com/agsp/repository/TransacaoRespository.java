package com.agsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agsp.entity.TransacaoEntity;

public interface TransacaoRespository extends JpaRepository<TransacaoEntity, Long> {

}
