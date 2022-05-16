package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.RecuperarSenhaCandidato;

@Repository
public interface RecuperarSenhaCandidatoRepository extends JpaRepository<RecuperarSenhaCandidato, Long> {

	Optional<RecuperarSenhaCandidato> findByCodigo(Integer codigo);

}
