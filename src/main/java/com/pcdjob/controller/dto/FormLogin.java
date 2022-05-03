package com.pcdjob.controller.dto;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;

public class FormLogin {
	private String login;
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
	
	public boolean autenticaCandidato(CandidatoEntity candidato) {
		if(candidato.getEmailCandidato().get(0).getEmail().equals(login) && candidato.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean autenticaEmpresa(EmpresaEntity empresa) {
		if(empresa.getEmailEmpresa().get(0).getEmail().equals(login) && empresa.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
}
