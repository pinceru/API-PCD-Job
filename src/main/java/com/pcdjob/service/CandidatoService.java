package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.candidato.Genero;
import com.pcdjob.model.candidato.TelefoneCandidato;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.GeneroRepository;
import com.pcdjob.repository.TelefoneCandidatoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class CandidatoService {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private TelefoneCandidatoRepository telefoneCandidatoRepository;
	
	@Autowired
	private EmailCandidatoRepository emailCandidatoRepository;
	
	public Genero recuperarGenero(String genero) {
		return generoRepository.findByGenero(genero);
	}
	
	public void deletarCandidato(Long id) {
		candidatoRepository.deleteById(id);
	}
	
	public EmailCandidato converterEmail(CandidatoEntity candidato, String email) {
		Optional<EmailCandidato> optional = emailCandidatoRepository.findByEmail(email);
		if(!Verificar.verificarOptional(optional)) {
			return emailCandidatoRepository.save(new EmailCandidato(email, candidato));
		} else {
			return null;
		}
	}
	
	public CandidatoEntity buscarCandidatoID(Long id) {
		Optional<CandidatoEntity> optional = candidatoRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void salvarTelefones(CandidatoEntity candidato, List<String> telefones) {
		int indice = 0;
		while(indice < telefones.size()) {
			Optional<TelefoneCandidato> optional = telefoneCandidatoRepository.findByNumero(telefones.get(indice));
			if(!Verificar.verificarOptional(optional)) {
				telefoneCandidatoRepository.save(new TelefoneCandidato(candidato, telefones.get(indice)));
			}
			indice += 1;
		}
	}
	
	public void salvarEmails(CandidatoEntity candidato, List<String> emails) {
		int indice = 0;
		while(indice < emails.size()) {
			Optional<EmailCandidato> optional = emailCandidatoRepository.findByEmail(emails.get(indice));
			if(!Verificar.verificarOptional(optional)) {
				emailCandidatoRepository.save(new EmailCandidato(emails.get(indice), candidato));
			}
			indice += 1;
		}
	}
}
