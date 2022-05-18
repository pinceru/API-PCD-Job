package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EmailForm {
	@NotNull @NotEmpty @Length(min = 11, max = 50)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String converter() {
		return email;
	}
}
