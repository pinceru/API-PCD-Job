package com.pcdjob.controller.dto;

import java.util.Optional;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.candidato.Genero;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.GeneroRepository;


public class InserirCandidatoDTO {
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
	
	public CandidatoEntity converter(GeneroRepository generoRepository) {
		Genero generoObj = generoRepository.findByGenero(genero);
		return new CandidatoEntity(nome, senha, generoObj);
	}
	
	public EmailCandidato converterEmail(EmailCandidatoRepository emailRepository, CandidatoEntity candidato) {
		Optional<EmailCandidato> emailCandidato = emailRepository.findByEmail(email);
		if(emailCandidato.isPresent() != true) {
			return emailRepository.save(new EmailCandidato(email, candidato));
		} else {
			return null;
		}
	}
}
