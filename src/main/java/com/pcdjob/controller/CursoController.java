package com.pcdjob.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	
	@GetMapping("/nivel/listar")
	@Transactional
	public Page<NivelDTO> listarNiveis(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Nivel> niveis = nivelRepository.findAll(paginacao);
		return NivelDTO.converter(niveis);
	}
	
	@GetMapping("/area/listar")
	@Transactional
	public Page<AreaDTO> listarAreas(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<AreaAtuacao> areas = areaRepository.findAll(paginacao);
		return AreaDTO.converter(areas);
	}
	
	@GetMapping("/listar")
	@Transactional
	public Page<CursoDTO> listarCursos(@RequestParam(required = true) String areaAtuacao, @RequestParam(required = true) String nivel, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		AreaAtuacao area = areaRepository.findByAreaAtuacao(areaAtuacao);
		Nivel nivelCurso = nivelRepository.findByNivel(nivel);
		Page<Curso> cursos = cursoRepository.findByAreaAtuacaoAndNivel(area, nivelCurso, paginacao);
		return CursoDTO.converter(cursos);
	}

}
