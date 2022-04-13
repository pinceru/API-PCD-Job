package com.pcdjob.controller.dto.response;

public class ResponseHorario {
	private Long id;
	private String horarioInicio;
	private String horarioFinal;
	private int status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioFinal() {
		return horarioFinal;
	}
	public void setHorarioFinal(String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public ResponseHorario() {
		
	}
	
	public ResponseHorario(Long id, String horarioInicio, String horarioFinal, int status) {
		this.id = id;
		this.horarioFinal = horarioFinal;
		this.horarioInicio = horarioInicio;
		this.status = status;
	}
}
