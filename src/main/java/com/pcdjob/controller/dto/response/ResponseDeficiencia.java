package com.pcdjob.controller.dto.response;

public class ResponseDeficiencia {
	private Long id;
	private String deficiencia;
	private Long idTipoDeficiencia;
	private String tipoDeficiencia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public ResponseDeficiencia() {
		
	}
	
	public ResponseDeficiencia(Long id, Long idTipoDeficiencia, String deficiencia, String tipoDeficiencia) {
		this.id = id;
		this.deficiencia = deficiencia;
		this.idTipoDeficiencia = idTipoDeficiencia;
		this.tipoDeficiencia = tipoDeficiencia;
	}
	
}
