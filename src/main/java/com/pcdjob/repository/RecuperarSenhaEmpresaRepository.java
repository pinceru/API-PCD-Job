package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.RecuperarSenhaEmpresa;

@Repository
public interface RecuperarSenhaEmpresaRepository extends JpaRepository<RecuperarSenhaEmpresa, Long> {
	
}
