package com.agsp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agsp.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity>findByCpf(String cpf);

	boolean existsByCpf(String cpf);

}
