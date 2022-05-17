package com.pcdjob.controller.form;

import com.pcdjob.model.empresa.EmpresaEntity;

public class AtualizarSenhaEmpresaForm {
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