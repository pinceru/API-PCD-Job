package com.pcdjob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CursoCandidato;

@Repository
public interface CursoCandidatoRepository extends JpaRepository<CursoCandidato, Long> {

	Page<CursoCandidato> findByCandidato(CandidatoEntity candidatoEntity, Pageable paginacao);

}
