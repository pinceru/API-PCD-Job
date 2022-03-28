package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.DeficienciaCandidato;

public interface DeficienciaCandidatoRepository extends JpaRepository<DeficienciaCandidato, Long>{

	Optional<DeficienciaCandidato> findByDeficienciaAndCandidato(Deficiencia idDeficiencia, CandidatoEntity idCandidato);

	Optional<DeficienciaCandidato> findByDeficiencia(Deficiencia deficiencia);

}
