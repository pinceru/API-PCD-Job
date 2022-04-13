package com.pcdjob.controller.dto.response;

public class ResponseDeficiencia {
	private Long idRelacionamento;
	private Long idDeficiencia;
	private String deficiencia;
	private Long idTipoDeficiencia;
	private String tipoDeficiencia;
	
	public Long getId() {
		return idDeficiencia;
	}
	public void setId(Long id) {
		this.idDeficiencia = id;
	}
	public Long getIdTipoDeficiencia() {
		return idTipoDeficiencia;
	}
	public void setIdTipoDeficiencia(Long idTipoDeficiencia) {
		this.idTipoDeficiencia = idTipoDeficiencia;
	}
	public String getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}
	public String getTipoDeficiencia() {
		return tipoDeficiencia;
	}
	public void setTipoDeficiencia(String tipoDeficiencia) {
		this.tipoDeficiencia = tipoDeficiencia;
	}
	public Long getIdRelacionamento() {
		return idRelacionamento;
	}
	public void setIdRelacionamento(Long idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}
	public ResponseDeficiencia() {
		
	}
	
	public ResponseDeficiencia(Long id, Long idTipoDeficiencia, String deficiencia, String tipoDeficiencia, Long idRelacionamento) {
		this.idDeficiencia = id;
		this.deficiencia = deficiencia;
		this.idTipoDeficiencia = idTipoDeficiencia;
		this.tipoDeficiencia = tipoDeficiencia;
		this.idRelacionamento = idRelacionamento;
	}
	
}
