package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.empresa.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
	Optional<EmpresaEntity> findByEmailEmpresaEmail(String email);

	Optional<Page<EmpresaEntity>> findByNomeOrAreaAtuacaoAreaAtuacao(String palavra, Pageable paginacao);
}
