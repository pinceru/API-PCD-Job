package com.pcdjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.vaga.StatusVaga;
import com.pcdjob.model.vaga.VagaCandidato;
import com.pcdjob.model.vaga.VagaEntity;

@Repository
public interface VagaCandidatoRepository extends JpaRepository<VagaCandidato, Long> {

	List<VagaCandidato> findByVagaAndStatus(VagaEntity vaga, StatusVaga status);

	List<VagaCandidato> findByCandidatoAndStatus(CandidatoEntity candidato, StatusVaga status);

	List<VagaCandidato> findByCandidato(CandidatoEntity candidato);

	List<VagaCandidato> findByCandidatoNot(CandidatoEntity candidato);

}
