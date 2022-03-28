package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EmailCandidato;

public interface EmailCandidatoRepository extends JpaRepository<EmailCandidato, Long> {
	Optional<EmailCandidato> findByEmail(String email);
	Optional<EmailCandidato> findByEmailAndCandidato(String email, CandidatoEntity candidato);
}
