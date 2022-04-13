package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.FormacaoDesejada;

@Repository
public interface FormacaoDesejadaRepository extends JpaRepository<FormacaoDesejada, Long>{

}
