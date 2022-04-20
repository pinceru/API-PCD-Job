package com.pcdjob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.VagaEntity;

@Repository
public interface VagaRepository extends JpaRepository<VagaEntity, Long>{
	Page<VagaEntity> findAllByEmpresa(EmpresaEntity empresaEntity, Pageable paginacao);
}
