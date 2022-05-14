package com.pcdjob.controller.form;

public class SenhaForm {
	private String email;
	private Integer codigo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String converter() {
		return email;
	}
	
	public Integer recuperarCodigo() {
		return codigo;
	}
}
