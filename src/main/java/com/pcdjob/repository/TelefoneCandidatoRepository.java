package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.candidato.TelefoneCandidato;

public interface TelefoneCandidatoRepository extends JpaRepository<TelefoneCandidato, Long>{
	Optional<TelefoneCandidato> findByNumero(String telefone);
}
