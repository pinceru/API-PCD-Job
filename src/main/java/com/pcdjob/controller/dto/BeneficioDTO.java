package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.vaga.Beneficio;

public class BeneficioDTO {
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
	
	public BeneficioDTO(Beneficio beneficio) {
		this.id = beneficio.getId();
		this.beneficio = beneficio.getBeneficio();
	}
	
	public static Page<BeneficioDTO> converter(Page<Beneficio> beneficios) {
		return beneficios.map(BeneficioDTO::new);
	}
}
