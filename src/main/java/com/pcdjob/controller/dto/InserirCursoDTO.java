package com.pcdjob.controller.dto;

import java.util.Optional;

import com.pcdjob.model.Curso;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CursoCandidato;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.CursoRepository;

public class InserirCursoDTO {
	private Long curso;

	public Long getCurso() {
		return curso;
	}

	public void setCurso(Long curso) {
		this.curso = curso;
	}
	
	public CursoCandidato converter(Long id, CandidatoRepository candidatoRepository, CursoRepository cursoRepository) {
		Optional<Curso> cursoObj = cursoRepository.findById(curso);
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(id);
		return new CursoCandidato(candidato.get(), cursoObj.get());
	}
}

