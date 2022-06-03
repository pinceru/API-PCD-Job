package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.TipoContrato;

@Repository
public interface TipoContratoRepository extends JpaRepository<TipoContrato, Long>{

	Optional<TipoContrato> findByTipoContrato(String palavra);

	Optional<TipoContrato> findByTipoContratoContaining(String palavra);

}
