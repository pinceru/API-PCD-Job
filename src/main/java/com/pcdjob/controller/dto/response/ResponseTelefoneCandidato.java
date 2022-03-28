package com.pcdjob.controller.dto.response;

public class ResponseTelefoneCandidato {

	private Long id;
	private String numero;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public ResponseTelefoneCandidato() {
		
	}
	
	public ResponseTelefoneCandidato(Long id, String numero) {
		this.id = id;
		this.numero = numero;
	}
}
