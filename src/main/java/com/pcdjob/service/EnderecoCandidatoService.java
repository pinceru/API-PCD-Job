package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.EnderecoCandidato;
import com.pcdjob.repository.EnderecoCandidatoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class EnderecoCandidatoService {
	
	@Autowired
	private EnderecoCandidatoRepository enderecoCandidatoRepository;
	
	public EnderecoCandidato buscarEnderecoCandidatoID(Long id) {
		Optional<EnderecoCandidato> optional = enderecoCandidatoRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
}
