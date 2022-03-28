package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.empresa.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

}
