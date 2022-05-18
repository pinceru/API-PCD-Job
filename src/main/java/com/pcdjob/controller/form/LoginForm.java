package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LoginForm {
	@NotNull @NotEmpty @Length(max = 50)
	private String login;
	@NotNull @NotEmpty @Length(max = 20)
	private String senha;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	 
}
