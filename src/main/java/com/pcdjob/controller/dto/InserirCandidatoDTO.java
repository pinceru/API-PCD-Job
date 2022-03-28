package com.pcdjob.controller.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.candidato.Genero;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.GeneroRepository;


public class InserirCandidatoDTO {
	@NotNull @NotEmpty @Length(min = 1, max = 100)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5, max = 100)
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
		Optional<EmailCandidato> emailCandidato = emailRepository.findByEmailAndCandidato(email, candidato);
		if(emailCandidato.isPresent()) {
			//emailRepository.delete(emailCandidato.get());
			return emailRepository.save(emailCandidato.get());
		} else {
			return emailRepository.save(new EmailCandidato(email, candidato));
			
		}
	}
}
