package com.pcdjob.controller;

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
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.NivelRepository;
import com.pcdjob.service.AreaAtuacaoService;
import com.pcdjob.service.CursoService;
import com.pcdjob.service.NivelService;
import com.pcdjob.service.helper.NotFoundException;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private NivelRepository nivelRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private NivelService nivelService;
	
	@Autowired
	private AreaAtuacaoService areaService;
	
	@Autowired
	private CursoService cursoService;
	
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
		Page<AreaAtuacao> areas = areaService.paginarAreaAtuacao(paginacao);
		return AreaDTO.converter(areas);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<CursoDTO> listarCursos(@RequestParam(required = false) Long idAreaAtuacao, @RequestParam(required = false) Long idNivel, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if(idAreaAtuacao != null && idNivel != null) {
			try {
				AreaAtuacao area = areaService.buscarAreaID(idAreaAtuacao);
				Nivel nivelCurso = nivelService.buscarNivelID(idNivel);
				Page<Curso> cursos = cursoService.paginarCursoAreaNivel(area, nivelCurso, paginacao);
				return CursoDTO.converter(cursos);
			} catch(Exception e) {
				System.out.println("Ocorreu uma Exception em " + e.getClass());
			} 
		} 
		
		if(idAreaAtuacao != null && idNivel == null) {
			try {
				AreaAtuacao area = areaService.buscarAreaID(idAreaAtuacao);
				Page<Curso> cursos = cursoService.paginarCursoArea(area, paginacao);
				return CursoDTO.converter(cursos);
			} catch(NotFoundException e) {
				e.mensagem();
				System.out.println("Ocorreu uma Exception em " + e.getClass());
			}
		} 
		
		if(idAreaAtuacao == null && idNivel != null) {
			try {
				Nivel nivelCurso = nivelService.buscarNivelID(idNivel);
				Page<Curso> cursos = cursoService.paginarCursoNivel(nivelCurso, paginacao);
				return CursoDTO.converter(cursos);
			} catch(NotFoundException e) {
				e.mensagem();
				System.out.println("Ocorreu uma Exception em " + e.getClass());
			}
		} 
		
		Page<Curso> cursos = cursoRepository.findAll(paginacao);
		return CursoDTO.converter(cursos);
	}
}
