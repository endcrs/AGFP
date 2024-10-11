package com.agsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agsp.entity.LoginUsuarioEntity;

public interface LoginUsuarioRepository extends JpaRepository<LoginUsuarioEntity, Long> {

//	Optional<LoginUsuarioEntity> findByToken(String token);

}
