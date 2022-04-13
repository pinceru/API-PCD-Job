package com.pcdjob.controller.dto.response;

public class ResponseSuporte {
	private Long id;
	private String suporte;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSuporte() {
		return suporte;
	}
	public void setSuporte(String suporte) {
		this.suporte = suporte;
	}
	public ResponseSuporte(Long id, String suporte) {
		this.id = id;
		this.suporte = suporte;
	}
	
}
