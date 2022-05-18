package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.empresa.EmpresaEntity;

public class AtualizarSenhaEmpresaForm {
	@NotNull @NotEmpty @Length(min = 3, max = 20)
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String converter() {
		return senha;
	}
	
	public EmpresaEntity converter(EmpresaEntity empresa, String senha) {
		empresa.setSenha(senha);
		return empresa;
	}
}