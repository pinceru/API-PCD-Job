package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.TelefoneEmpresa;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.TelefoneEmpresaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmailEmpresaRepository emailEmpresaRepository;
	
	@Autowired
	private TelefoneEmpresaRepository telefoneEmpresaRepository;
	
	public EmpresaEntity buscarEmpresaID(Long id) {
		Optional<EmpresaEntity> optional = empresaRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void deletarEmpresa(EmpresaEntity empresa) {
		empresaRepository.delete(empresa);
	}
	
	public void converterEmail(List<String> email, EmpresaEntity empresa) {
		int indice = 0;
		while(indice < email.size()) {
			Optional<EmailEmpresa> optional = emailEmpresaRepository.findByEmail(email.get(indice));
			if(!Verificar.verificarOptional(optional)) {
				emailEmpresaRepository.save(new EmailEmpresa(email.get(indice), empresa));
			}
			indice += 1;
		}
	}
	
	public void converterTelefone(EmpresaEntity empresa, List<String> telefone) {
		int indice = 0;
		while(indice < telefone.size()) {
			Optional<TelefoneEmpresa> optional = telefoneEmpresaRepository.findByNumero(telefone.get(indice));
			if(optional.isPresent() != true) {
				telefoneEmpresaRepository.save(new TelefoneEmpresa(telefone.get(indice), empresa));
			}
			indice += 1;
		}
	}
	
	public List<TelefoneEmpresa> atualizarTelefones(List<String> telefone, EmpresaEntity empresa) {
		List<TelefoneEmpresa> listaTelefone = new ArrayList<>();
		int indice = 0;
		while(telefone.size() > indice) {
			Optional<TelefoneEmpresa> optional = telefoneEmpresaRepository.findByEmpresaAndNumero(empresa, telefone.get(indice));
			if(Verificar.verificarOptional(optional)) {
				listaTelefone.add(optional.get());
			} else {
				TelefoneEmpresa telefoneObj = new TelefoneEmpresa(telefone.get(indice), empresa);
				listaTelefone.add(telefoneEmpresaRepository.save(telefoneObj));
			}
			indice++;
		}
		return listaTelefone;
	}
	
	public List<EmailEmpresa> atualizarEmails(List<String> email, EmpresaEntity empresa) {
		List<EmailEmpresa> listaEmail = new ArrayList<>();
		int indice = 0;
		while(email.size() > indice) {
			Optional<EmailEmpresa> optional = emailEmpresaRepository.findByEmpresaAndEmail(empresa, email.get(indice));
			if(Verificar.verificarOptional(optional)) {
				listaEmail.add(optional.get());
			} else {
				EmailEmpresa emailObj = new EmailEmpresa(email.get(indice), empresa);
				listaEmail.add(emailEmpresaRepository.save(emailObj));
			}
			indice++;
		}
		return listaEmail;
	}
	
	public Optional<EmailEmpresa> verificarEmail(String email) {
		return emailEmpresaRepository.findByEmail(email);
	}
}
