package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Nivel;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public Curso buscarCursoID(Long id) {
		Optional<Curso> optional = cursoRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Page<Curso> paginarCursoAreaNivel(AreaAtuacao area, Nivel nivel, Pageable paginacao) {
		return cursoRepository.findByAreaAtuacaoAndNivel(area, nivel, paginacao);
	}
	
	public Page<Curso> paginarCursoArea(AreaAtuacao area, Pageable paginacao) {
		return cursoRepository.findByAreaAtuacao(area, paginacao);
	}
	
	public Page<Curso> paginarCursoNivel(Nivel nivel, Pageable paginacao) {
		return cursoRepository.findByNivel(nivel, paginacao);
	}
}
