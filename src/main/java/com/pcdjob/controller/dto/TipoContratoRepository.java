package com.pcdjob.controller.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.TipoContrato;

@Repository
public interface TipoContratoRepository extends JpaRepository<TipoContrato, Long>{

}
