package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.TipoDeficiencia;

public class TipoDeficienciaDTO {

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
	
	public TipoDeficienciaDTO(TipoDeficiencia tipo) {
		this.id = tipo.getId();
		this.tipo = tipo.getTipo();
	}
	
	public static Page<TipoDeficienciaDTO> converter(Page<TipoDeficiencia> tipoDeficiencia) {
		return tipoDeficiencia.map(TipoDeficienciaDTO::new);
	}
}
