package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.vaga.TipoContrato;

public class ContratoDTO {
	private Long id;
	private String tipo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public ContratoDTO(TipoContrato tipo) {
		this.id = tipo.getId();
		this.tipo = tipo.getTipoContrato();
	}
	public static Page<ContratoDTO> converter(Page<TipoContrato> tipos) {
		return tipos.map(ContratoDTO::new);
	}
}
