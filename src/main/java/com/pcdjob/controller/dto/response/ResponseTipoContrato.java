package com.pcdjob.controller.dto.response;

public class ResponseTipoContrato {
	private Long id;
	private String tipoContrato;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTpoContrato() {
		return tipoContrato;
	}
	public void setTpoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	
	public ResponseTipoContrato(Long id, String tipoContrato) {
		this.id = id;
		this.tipoContrato = tipoContrato;
	}
}
