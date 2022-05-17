package com.pcdjob.controller.form;

import com.pcdjob.model.candidato.CandidatoEntity;

public class AtualizarSenhaCandidatoForm {
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

