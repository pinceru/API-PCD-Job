package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.EnderecoCandidato;

@Repository
public interface EnderecoCandidatoRepository extends JpaRepository<EnderecoCandidato, Long>{

}
