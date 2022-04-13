package com.pcdjob.controller.dto.response;

public class ResponseTipoContrato {
	private Long id;
	private String tpoContrato;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTpoContrato() {
		return tpoContrato;
	}
	public void setTpoContrato(String tpoContrato) {
		this.tpoContrato = tpoContrato;
	}
	
	public ResponseTipoContrato(Long id, String tipoContrato) {
		this.id = id;
		this.tpoContrato = tipoContrato;
	}
}
