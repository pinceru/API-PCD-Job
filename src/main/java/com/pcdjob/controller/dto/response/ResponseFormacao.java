package com.pcdjob.controller.dto.response;

public class ResponseFormacao {
	private Long idFormacaoDesejada;
	private Long idCurso;
	private String curso;
	private Long idAreaAtuacao;
	private String areaAtuacao;
	private Long idNivel;
	private String nivel;
	
	public Long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Long id) {
		this.idCurso = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Long getIdAreaAtuacao() {
		return idAreaAtuacao;
	}
	public void setIdAreaAtuacao(Long idAreaAtuacao) {
		this.idAreaAtuacao = idAreaAtuacao;
	}
	public String getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public Long getIdFormacaoDesejada() {
		return idFormacaoDesejada;
	}
	public void setIdFormacaoDesejada(Long idFormacaoDesejada) {
		this.idFormacaoDesejada = idFormacaoDesejada;
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
	public ResponseFormacao(Long id, String curso, Long idAreaAtuacao, String areaAtuacao, Long idFormacaoDesejada, Long idNivel, String nivel) {
		this.idCurso = id;
		this.curso = curso;
		this.idAreaAtuacao = idAreaAtuacao;
		this.areaAtuacao = areaAtuacao;
		this.idFormacaoDesejada = idFormacaoDesejada;
		this.idNivel = idNivel;
		this.nivel = nivel;
	}
}
