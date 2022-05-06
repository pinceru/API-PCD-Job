package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.EmpresaRepository;

@Service
public class AuthService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	public boolean autenticaCandidato(CandidatoEntity candidato, String login, String senha) {
		if(candidato.getEmailCandidato().get(0).getEmail().equals(login) && candidato.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean autenticaEmpresa(EmpresaEntity empresa, String login, String senha) {
		if(empresa.getEmailEmpresa().get(0).getEmail().equals(login) && empresa.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Optional<EmpresaEntity> buscarEmpresaEmail(String email) {
		return empresaRepository.findByEmailEmpresaEmail(email);
	}
	
	public Optional<CandidatoEntity> buscarCandidatoEmail(String email) {
		return candidatoRepository.findByEmailCandidatoEmail(email);
	}
	
}
