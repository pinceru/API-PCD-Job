package com.pcdjob.controller.dto.response;

public class ResponseEmailEmpresa {
	private Long id;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ResponseEmailEmpresa(Long id, String email) {
		this.id = id;
		this.email = email;
	}
}
