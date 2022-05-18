package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class SenhaForm {
	@NotNull @NotEmpty @Length(min = 11, max = 50)
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
