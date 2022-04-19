package com.pcdjob.controller.dto.response;

public class ResponseEmpresa {
	private Long id;
	private String empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public ResponseEmpresa(Long id, String empresa) {
		this.id = id;
		this.empresa = empresa;
	}
}
