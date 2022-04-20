package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Curso;
import com.pcdjob.model.vaga.FormacaoDesejada;
import com.pcdjob.model.vaga.VagaEntity;

@Repository
public interface FormacaoDesejadaRepository extends JpaRepository<FormacaoDesejada, Long>{

	Optional<FormacaoDesejada> findByCursoAndVaga(Curso curso, VagaEntity vaga);

	Optional<FormacaoDesejada> findByCurso(Curso curso);

}
