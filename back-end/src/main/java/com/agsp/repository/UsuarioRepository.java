package com.agsp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agsp.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	Optional<UsuarioEntity>findByCpf(String cpf);

	boolean existsByCpf(String cpf);

}
