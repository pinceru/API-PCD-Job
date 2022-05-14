package com.pcdjob.controller.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.Genero;


public class CandidatoForm {
	private String nome;
	private String senha;
	private String email;
	private String genero;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public CandidatoEntity converter(Genero generoObj) {
		String novaSenha = new BCryptPasswordEncoder().encode(senha);
		return new CandidatoEntity(nome, novaSenha, generoObj);
	}
}
