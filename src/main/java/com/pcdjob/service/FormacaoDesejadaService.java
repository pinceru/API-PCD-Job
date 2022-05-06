package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Curso;
import com.pcdjob.model.vaga.FormacaoDesejada;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.FormacaoDesejadaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class FormacaoDesejadaService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private FormacaoDesejadaRepository formacaoRepository;
	
	public void converterCurso(VagaEntity vaga, List<Long> cursosForm) {		
		int indice = 0;
		while(indice < cursosForm.size()) {
			Optional<Curso> curso = cursoRepository.findById(cursosForm.get(indice));
			Optional<FormacaoDesejada> optional = formacaoRepository.findByCursoAndVaga(curso.get(), vaga);
			if(!Verificar.verificarOptional(optional)) {
				formacaoRepository.save(new FormacaoDesejada(vaga, curso.get()));
			} else {
				List<Curso> cursos = cursoRepository.findAll();
				deletarCursos(cursos, cursosForm);
			}
			indice += 1;
		}
	}
	
	private boolean verificarCurso(int indice, List<Long> cursosForm, List<Curso> cursosExistentes) {
		if(cursosForm.contains(cursosExistentes.get(indice).getId())) {
			return true;
		} else {
			return false;
		}
	}
	
	private void deletarCursos(List<Curso> cursosExistentes, List<Long> cursosForm) {
		int indice = 0;
		while(indice < cursosExistentes.size()) {
			if(!verificarCurso(indice, cursosForm, cursosExistentes)) {
				Optional<FormacaoDesejada> optional = formacaoRepository.findByCurso(cursosExistentes.get(indice));
				if(Verificar.verificarOptional(optional)) {
					formacaoRepository.delete(optional.get());
				}
			}
			indice++;
		}
	}
}
