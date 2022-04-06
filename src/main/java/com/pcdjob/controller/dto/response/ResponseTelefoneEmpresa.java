package com.pcdjob.controller.dto.response;

public class ResponseTelefoneEmpresa {
	private Long id;
	private String telefone;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public ResponseTelefoneEmpresa(Long id, String telefone) {
		this.id = id;
		this.telefone = telefone;
	}
}
