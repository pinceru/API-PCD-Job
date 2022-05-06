package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.ExperienciaProfissional;
import com.pcdjob.repository.ExperienciaProfissionalRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class ExperienciaProfissionalService {
	
	@Autowired
	private ExperienciaProfissionalRepository experienciaRepository;
	
	public ExperienciaProfissional buscarExperienciaID(Long id) {
		Optional<ExperienciaProfissional> optional = experienciaRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void deletarExperienciaProfissional(Long id) {
		experienciaRepository.deleteById(id);
	}
}
