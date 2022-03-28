package com.pcdjob.controller.dto.response;

public class ResponseCursoCandidato {
	private Long id;
	private String curso;
	private Long idNivelCurso;
	private String nivelCurso;
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
	public Long getIdNivelCurso() {
		return idNivelCurso;
	}
	public void setIdNivelCurso(Long idNivelCurso) {
		this.idNivelCurso = idNivelCurso;
	}
	public String getNivelCurso() {
		return nivelCurso;
	}
	public void setNivelCurso(String nivelCurso) {
		this.nivelCurso = nivelCurso;
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
	
	public ResponseCursoCandidato() {
		
	}
	
	public ResponseCursoCandidato(Long id, String curso, Long idNivelCurso, String nivelCurso, Long idArea, String area) {
		this.area = area;
		this.curso = curso;
		this.id = id;
		this.idArea = idArea;
		this.idNivelCurso = idNivelCurso;
		this.nivelCurso = nivelCurso;
	}
}
