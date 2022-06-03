package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.empresa.EmpresaFoto;

@Repository
public interface EmpresaFotoRepository extends JpaRepository<EmpresaFoto, Long> {

}
