package com.pcdjob.controller.form;

import com.pcdjob.model.Curso;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CursoCandidato;

public class CursoForm {
	private Long curso;

	public Long getCurso() {
		return curso;
	}

	public void setCurso(Long curso) {
		this.curso = curso;
	}
	
	public CursoCandidato converter(CandidatoEntity candidato, Curso cursoObj) {
		return new CursoCandidato(candidato, cursoObj);
	}
}

