package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.Estado;

public class EstadoDTO {
	private Long id;
	private String estado;
	private String sigla;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.estado = estado.getEstado();
		this.sigla = estado.getSigla();
	}
	
	public static Page<EstadoDTO> converter(Page<Estado> estado) {
		return estado.map(EstadoDTO::new);
	}
}
