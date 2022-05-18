package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.Genero;


public class CandidatoForm {
	@NotNull @NotEmpty @Length(min = 2, max = 50)
	private String nome;
	@NotNull @NotEmpty @Length(min = 3, max = 35)
	private String senha;
	@NotNull @NotEmpty @Length(min = 11, max = 50)
	private String email;
	@NotNull @NotEmpty
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
