package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.candidato.CandidatoEntity;

public class AtualizarSenhaCandidatoForm {
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
	
	public CandidatoEntity atualizarSenha(CandidatoEntity candidato, String senha) {
		candidato.setSenha(senha);
		return candidato;
	}
}

