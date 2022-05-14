package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.empresa.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
	Optional<EmpresaEntity> findByEmailEmpresaEmail(String email);

	Optional<EmpresaEntity> findByNome(String palavra);
}
