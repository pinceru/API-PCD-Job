package com.pcdjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CandidatoFoto;
import com.pcdjob.repository.CandidatoFotoRepository;
import com.pcdjob.repository.EmpresaFotoRepository;

@Service
public class FotoService {
	@Autowired
	private CandidatoFotoRepository candidatoFotoRepository;
	
	@Autowired
	private EmpresaFotoRepository empresaFotoRepository;

	public void salvarImagemCandidato(byte[] foto, CandidatoEntity candidato) {
		candidatoFotoRepository.save(new CandidatoFoto(foto, candidato));
		
	}

	public CandidatoFoto buscarFotoCandidatoID(Long id) {
		return candidatoFotoRepository.findById(id).get();
	}
	

}
