package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.Curso;

public class CursoDTO {
	private Long id;
	private String curso;
	private Long idNivel;
	private String nivel;
	private Long idArea;
	private String area;
	
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

	public Long getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Long idNivel) {
		this.idNivel = idNivel;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public CursoDTO() {
		
	}
	
	public CursoDTO(Curso curso) {
		this.id = curso.getId();
		this.curso = curso.getCurso();
		this.area = curso.getAreaAtuacao().getAreaAtuacao();
		this.idArea = curso.getAreaAtuacao().getId();
		this.idNivel = curso.getNivel().getId();
		this.nivel = curso.getNivel().getNivel();
	}
	
	public static Page<CursoDTO> converter(Page<Curso> cursos) {
		return cursos.map(CursoDTO::new);
	}
}
