package com.pcdjob.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.AreaDTO;
import com.pcdjob.controller.dto.CursoDTO;
import com.pcdjob.controller.dto.NivelDTO;
import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Nivel;
import com.pcdjob.repository.AreaAtuacaoRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.NivelRepository;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private NivelRepository nivelRepository;
	
	@Autowired
	private AreaAtuacaoRepository areaRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@CrossOrigin
	@GetMapping(path = "/nivel/listar", produces = "application/json")
	@Transactional
	public Page<NivelDTO> listarNiveis(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Nivel> niveis = nivelRepository.findAll(paginacao);
		return NivelDTO.converter(niveis);
	}
	
	@CrossOrigin
	@GetMapping(path = "/area/listar", produces = "application/json")
	@Transactional
	public Page<AreaDTO> listarAreas(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<AreaAtuacao> areas = areaRepository.findAll(paginacao);
		return AreaDTO.converter(areas);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<CursoDTO> listarCursos(@RequestParam(required = false) Long idAreaAtuacao, @RequestParam(required = false) Long idNivel, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if(idAreaAtuacao != null && idNivel != null) {
			Optional<AreaAtuacao> area = areaRepository.findById(idAreaAtuacao);
			Optional<Nivel> nivelCurso = nivelRepository.findById(idNivel);
			Page<Curso> cursos = cursoRepository.findByAreaAtuacaoAndNivel(area.get(), nivelCurso.get(), paginacao);
			return CursoDTO.converter(cursos);
		} else if(idAreaAtuacao != null && idNivel == null) {
			Optional<AreaAtuacao> area = areaRepository.findById(idAreaAtuacao);
			Page<Curso> cursos = cursoRepository.findByAreaAtuacao(area.get(), paginacao);
			return CursoDTO.converter(cursos);
		} else if(idAreaAtuacao == null && idNivel != null) {
			Optional<Nivel> nivelCurso = nivelRepository.findById(idNivel);
			Page<Curso> cursos = cursoRepository.findByNivel(nivelCurso.get(), paginacao);
			return CursoDTO.converter(cursos);
		} else {
			Page<Curso> cursos = cursoRepository.findAll(paginacao);
			return CursoDTO.converter(cursos);
		}
	}

}
