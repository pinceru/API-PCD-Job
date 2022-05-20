package com.pcdjob.controller.dto.response;

public class ResponseSalario {
	private Long id;
	private Float salario;
	private int status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ResponseSalario(Long id, Float salario, int status) {
		this.id = id;
		this.salario = salario;
		this.status = status;
	}
}
