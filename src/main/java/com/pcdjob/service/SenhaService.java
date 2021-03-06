package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.RecuperarSenhaCandidato;
import com.pcdjob.model.RecuperarSenhaEmpresa;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.RecuperarSenhaCandidatoRepository;
import com.pcdjob.repository.RecuperarSenhaEmpresaRepository;
import com.pcdjob.service.helper.NotFoundException;
import com.pcdjob.service.helper.Verificar;

@Service
public class SenhaService {
	
	@Autowired
	private RecuperarSenhaEmpresaRepository recuperarEmpresaRepository;
	
	@Autowired
	private RecuperarSenhaCandidatoRepository recuperarCandidatoRepository;
	
	@Autowired
	private EmailEmpresaRepository emailEmpresaRepository;
	
	@Autowired
	private EmailCandidatoRepository emailCandidatoRepository;
	
	public void salvarRelacao(Optional<EmailEmpresa> emailEmpresa, Optional<EmailCandidato> emailCandidato, Integer codigo) {
		if(Verificar.verificarOptional(emailEmpresa)) {
			recuperarEmpresaRepository.save(new RecuperarSenhaEmpresa(codigo, emailEmpresa.get()));
		}
		
		if(Verificar.verificarOptional(emailCandidato)) {
			recuperarCandidatoRepository.save(new RecuperarSenhaCandidato(codigo, emailCandidato.get()));
		}
	}
	
	public EmailEmpresa compararCodigoEmpresa(Integer codigo) {
		Optional<RecuperarSenhaEmpresa> empresa = recuperarEmpresaRepository.findByCodigo(codigo);
		
		if(!Verificar.verificarOptional(empresa)) {
			throw new NotFoundException();
		}
		return emailEmpresaRepository.findByRecuperar(empresa.get());
	}
	
	public EmailCandidato compararCodigoCandidato(Integer codigo) {
		Optional<RecuperarSenhaCandidato> candidato = recuperarCandidatoRepository.findByCodigo(codigo);
		
		if(!Verificar.verificarOptional(candidato)) {
			throw new NotFoundException();
		}
		return emailCandidatoRepository.findByRecuperar(candidato.get());
	}
	
}


