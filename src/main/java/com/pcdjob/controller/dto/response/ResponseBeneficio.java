package com.pcdjob.controller.dto.response;

public class ResponseBeneficio {
	private Long idVagaBeneficio;
	private Long idBeneficio;
	private String beneficio;
	
	public Long getIdBeneficio() {
		return idBeneficio;
	}
	public void setIdBeneficio(Long id) {
		this.idBeneficio = id;
	}
	public String getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	public Long getIdVagaBeneficio() {
		return idVagaBeneficio;
	}
	public void setIdVagaBeneficio(Long idVagaBeneficio) {
		this.idVagaBeneficio = idVagaBeneficio;
	}
	public ResponseBeneficio(Long id, String beneficio, Long idVagaBeneficio) {
		this.idBeneficio = id;
		this.beneficio = beneficio;
		this.idVagaBeneficio = idVagaBeneficio;
	}
}
