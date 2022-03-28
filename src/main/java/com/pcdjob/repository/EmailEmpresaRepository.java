package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.empresa.EmailEmpresa;

public interface EmailEmpresaRepository extends JpaRepository<EmailEmpresa, Long> {
	Optional<EmailEmpresa> findByEmail(String email);
}
