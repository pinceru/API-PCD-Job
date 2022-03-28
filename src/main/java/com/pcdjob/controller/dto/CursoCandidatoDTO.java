package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.candidato.CursoCandidato;

public class CursoCandidatoDTO {
	private Long id;
	private String curso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public CursoCandidatoDTO(CursoCandidato cursoCandidato) {
		this.id = cursoCandidato.getId();
		this.curso = cursoCandidato.getCurso().getCurso();
	}
	
	public static Page<CursoCandidatoDTO> converter(Page<CursoCandidato> cursos) {
		return cursos.map(CursoCandidatoDTO::new);
	}
}
