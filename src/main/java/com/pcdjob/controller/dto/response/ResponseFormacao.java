package com.pcdjob.controller.dto.response;

public class ResponseFormacao {
	private Long idFormacaoDesejada;
	private Long idCurso;
	private String curso;
	private Long idAreaAtuacao;
	private String areaAtuacao;
	
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
	public ResponseFormacao(Long id, String curso, Long idAreaAtuacao, String areaAtuacao, Long idFormacaoDesejada) {
		this.idCurso = id;
		this.curso = curso;
		this.idAreaAtuacao = idAreaAtuacao;
		this.areaAtuacao = areaAtuacao;
		this.idFormacaoDesejada = idFormacaoDesejada;
	}
}
