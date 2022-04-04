package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.Deficiencia;

public class DeficienciaDTO {

	private Long id;
	private String deficiencia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}

	public DeficienciaDTO(Deficiencia deficiencia) {
		this.id = deficiencia.getId();
		this.deficiencia = deficiencia.getDeficiencia();
	}
	
	public static Page<DeficienciaDTO> converter(Page<Deficiencia> deficiencias) {
		return deficiencias.map(DeficienciaDTO::new);
	}

}
