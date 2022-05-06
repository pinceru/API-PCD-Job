package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CursoCandidato;
import com.pcdjob.repository.CursoCandidatoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class CursoCandidatoService {
	
	@Autowired
	private CursoCandidatoRepository cursoCandidatoRepository;
	
	public Page<CursoCandidato> paginarCursosCandidato(Pageable paginacao, CandidatoEntity candidato) {
		return cursoCandidatoRepository.findByCandidato(candidato, paginacao);
	}
	
	public CursoCandidato buscarCursoCandidatoID(Long id) {
		Optional<CursoCandidato> optional = cursoCandidatoRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void deletarCursoCandidato(Long id) {
		cursoCandidatoRepository.deleteById(id);
	}
}
