package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.Beneficio;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long>{

}
