package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class EmailCadastroService {
	
	@Autowired
	private EmailCandidatoRepository emailCandidatoRepository;
	
	@Autowired
	private EmailEmpresaRepository emailEmpresaRepository;
	
	public EmailCandidato verificarEmailCandidato(String email) {
		Optional<EmailCandidato> optional = emailCandidatoRepository.findByEmail(email);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public EmailEmpresa verificarEmailEmpresa(String email) {
		Optional<EmailEmpresa> optional = emailEmpresaRepository.findByEmail(email);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
}
