package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.Nivel;

public class NivelDTO {
	private Long id;
	private String nivel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	public NivelDTO() {
		
	}
	
	public NivelDTO(Nivel nivel) {
		this.id = nivel.getId();
		this.nivel = nivel.getNivel();
	}
	public static Page<NivelDTO> converter(Page<Nivel> niveis) {
		return niveis.map(NivelDTO::new);
	}
}
