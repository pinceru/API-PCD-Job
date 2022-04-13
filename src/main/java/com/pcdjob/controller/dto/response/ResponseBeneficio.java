package com.pcdjob.controller.dto.response;

public class ResponseBeneficio {
	private Long id;
	private String beneficio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	
	public ResponseBeneficio(Long id, String beneficio) {
		this.id = id;
		this.beneficio = beneficio;
	}
}
