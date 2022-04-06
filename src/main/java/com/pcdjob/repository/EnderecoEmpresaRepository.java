package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.empresa.EnderecoEmpresa;

@Repository
public interface EnderecoEmpresaRepository extends JpaRepository<EnderecoEmpresa, Long>{

}
