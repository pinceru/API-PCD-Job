package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.CandidatoFoto;

@Repository
public interface CandidatoFotoRepository extends JpaRepository<CandidatoFoto, Long> {

}
