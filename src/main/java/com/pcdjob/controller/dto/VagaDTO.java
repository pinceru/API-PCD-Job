package com.pcdjob.controller.dto;

import com.pcdjob.model.vaga.VagaEntity;

public class VagaDTO {

	private Long id;
	private String titulo;
	private String horarioInicio;
	private String horarioSaida;
	private String empresa;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getHorarioInicio() {
		return horarioInicio;
	}



	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}



	public String getHorarioSaida() {
		return horarioSaida;
	}



	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}



	public String getEmpresa() {
		return empresa;
	}



	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}



	public VagaDTO(VagaEntity vaga) {
		this.id = vaga.getId();
		this.empresa = vaga.getEmpresa().getNome();
		this.horarioInicio = vaga.getHorario().getHorarioInicio();
		this.horarioSaida = vaga.getHorario().getHorarioSaida();
		this.titulo = vaga.getTitulo();
	}

}
