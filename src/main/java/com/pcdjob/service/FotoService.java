package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CandidatoFoto;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EmpresaFoto;
import com.pcdjob.repository.CandidatoFotoRepository;
import com.pcdjob.repository.EmpresaFotoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class FotoService {
	@Autowired
	private CandidatoFotoRepository candidatoFotoRepository;
	
	@Autowired
	private EmpresaFotoRepository empresaFotoRepository;

	public void salvarImagemCandidato(byte[] foto, CandidatoEntity candidato) {
		candidatoFotoRepository.save(new CandidatoFoto(foto, candidato));
	}
	
	public void salvarImagemEmpresa(byte[] foto, EmpresaEntity empresa) {
		empresaFotoRepository.save(new EmpresaFoto(foto, empresa));		
	}

	public CandidatoFoto buscarFotoCandidatoID(CandidatoEntity candidato) {
		Optional<CandidatoFoto> optional = candidatoFotoRepository.findByCandidato(candidato);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			throw new NullPointerException();
		}
	}

	public EmpresaFoto buscarFotoEmrpesaID(EmpresaEntity empresa) {
		Optional<EmpresaFoto> optional = empresaFotoRepository.findByEmpresa(empresa);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			throw new NullPointerException();
		}
	}
}
