package com.pcdjob.controller.dto.response;

public class ResponseCursoCandidato {
	private Long idCursoCandidato;
	private Long idCurso;
	private String curso;
	private Long idNivelCurso;
	private String nivelCurso;
	private Long idArea;
	private String area;
	
	public Long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
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
	public Long getIdCursoCandidato() {
		return idCursoCandidato;
	}
	public void setIdCursoCandidato(Long idCursoCandidato) {
		this.idCursoCandidato = idCursoCandidato;
	}
	public ResponseCursoCandidato() {
		
	}
	
	public ResponseCursoCandidato(Long id, String curso, Long idNivelCurso, String nivelCurso, Long idArea, String area, Long idCursoCandidato) {
		this.area = area;
		this.curso = curso;
		this.idCurso = id;
		this.idArea = idArea;
		this.idNivelCurso = idNivelCurso;
		this.nivelCurso = nivelCurso;
		this.idCursoCandidato = idCursoCandidato;
	}
}
