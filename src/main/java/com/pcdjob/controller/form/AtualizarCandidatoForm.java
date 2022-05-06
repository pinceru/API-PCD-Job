package com.pcdjob.controller.dto;

import java.util.List;
import java.util.Optional;

import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.DeficienciaCandidato;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.candidato.Genero;
import com.pcdjob.model.candidato.TelefoneCandidato;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.DeficienciaCandidatoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.GeneroRepository;
import com.pcdjob.repository.TelefoneCandidatoRepository;

public class AtualizarCandidatoDTO {
	private String nome;
	private String nomeSocial;
	private String dataNascimento;
	private String informacoes;
	private String genero;
	private List<Long> deficiencia;
	private List<String> telefone;
	private List<String> email;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeSocial() {
		return nomeSocial;
	}
	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public String getGenero() {
		return genero;
	}
	public void setId_genero(String genero) {
		this.genero = genero;
	}
	public List<Long> getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(List<Long> deficiencia) {
		this.deficiencia = deficiencia;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	

	public CandidatoEntity converter(Long id, CandidatoRepository candidatoRepository, GeneroRepository generoRepository) {
		System.out.println("O id veio como: " + deficiencia.get(0));
		CandidatoEntity candidato = candidatoRepository.getOne(id);
		Genero generoObj = generoRepository.findByGenero(genero);
		candidato.setNome(this.nome);
		candidato.setDataNascimento(this.dataNascimento);
		candidato.setNomeSocial(this.nomeSocial);
		candidato.setInformacoes(this.informacoes);
		candidato.setGenero(generoObj);
		return candidato;
	}
	
	public void converterDeficiencia(CandidatoEntity candidato, DeficienciaRepository deficienciaRepository, DeficienciaCandidatoRepository deficienciaCandidatoRepository) {
		int indice = 0;
		while(indice < deficiencia.size()) {
			Optional<Deficiencia> deficienciaObj = deficienciaRepository.findById(deficiencia.get(indice));
			Optional<DeficienciaCandidato> optional = deficienciaCandidatoRepository.findByDeficienciaAndCandidato(deficienciaObj.get(), candidato);
			if(optional.isPresent() != true) {
				deficienciaCandidatoRepository.save(new DeficienciaCandidato(deficienciaObj.get(), candidato));
			} else {
				List<Deficiencia> deficiencias = deficienciaRepository.findAll();
				int indice2 = 0;
				while(indice2 < deficiencias.size()) {
					if(!deficiencia.contains(deficiencias.get(indice2).getId())) {
						Optional<DeficienciaCandidato> deficienciaCandidatoOptional = deficienciaCandidatoRepository.findByDeficiencia(deficiencias.get(indice));
						if(deficienciaCandidatoOptional.isPresent()) {
							DeficienciaCandidato deficienciaCandidato = deficienciaCandidatoOptional.get();
							deficienciaCandidatoRepository.delete(deficienciaCandidato);
						}
					}
					indice2++;
				}
			}
			indice += 1;
		}
	}
	
	public void converterTelefone(CandidatoEntity candidato, TelefoneCandidatoRepository telefoneRepository) {
		int indice = 0;
		while(indice < telefone.size()) {
			Optional<TelefoneCandidato> optional = telefoneRepository.findByNumero(telefone.get(indice));
			if(optional.isPresent() != true) {
				telefoneRepository.save(new TelefoneCandidato(candidato, telefone.get(indice)));
			}
			indice += 1;
		}
	}	
	
	public void converterEmail(CandidatoEntity candidato, EmailCandidatoRepository emailRepository) {
		int indice = 0;
		while(indice < email.size()) {
			Optional<EmailCandidato> optional = emailRepository.findByEmail(email.get(indice));
			if(optional.isPresent() != true) {
				emailRepository.save(new EmailCandidato(email.get(indice), candidato));
			}
			indice += 1;
		}
	}
	
}

