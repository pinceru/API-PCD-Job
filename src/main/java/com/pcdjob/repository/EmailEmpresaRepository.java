package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;

public interface EmailEmpresaRepository extends JpaRepository<EmailEmpresa, Long> {
	Optional<EmailEmpresa> findByEmail(String email);

	Optional<EmailEmpresa> findByEmpresaAndEmail(EmpresaEntity empresa, String email);
}
