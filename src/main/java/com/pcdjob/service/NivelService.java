package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Nivel;
import com.pcdjob.repository.NivelRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class NivelService {
	
	@Autowired
	private NivelRepository nivelRepository;
	
	public Nivel buscarNivelID(Long id) {
		Optional<Nivel> optional = nivelRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
}
