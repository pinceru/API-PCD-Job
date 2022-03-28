package com.pcdjob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.CandidatoEntity;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {

	Page<CandidatoEntity> findByDeficienciaCandidato(String deficienciaCandidato, Pageable paginacao);


}
