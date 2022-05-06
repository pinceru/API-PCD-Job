package com.pcdjob.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.DeficienciaDTO;
import com.pcdjob.controller.dto.TipoDeficienciaDTO;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.TipoDeficiencia;
import com.pcdjob.repository.TipoDeficienciaRepository;
import com.pcdjob.service.DeficienciaService;

@RestController
@RequestMapping("/deficiencia")
public class DeficienciaController {

	@Autowired
	private TipoDeficienciaRepository tipoRepository;
	
	@Autowired
	private DeficienciaService deficienciaService;
	
	@CrossOrigin
	@GetMapping(path = "/listar/tipo", produces = "application/json")
	@Transactional
	public Page<TipoDeficienciaDTO> listarTipoDeficiencia(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<TipoDeficiencia> tipo = tipoRepository.findAll(paginacao);
		return TipoDeficienciaDTO.converter(tipo);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/{id}", produces = "application/json")
	@Transactional
	public Page<DeficienciaDTO> listarDeficienciaPorTipo(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		TipoDeficiencia tipo = deficienciaService.buscarTipoID(id);
		Page<Deficiencia> deficiencias = deficienciaService.paginarDeficienciasTipo(tipo, paginacao);
		return DeficienciaDTO.converter(deficiencias);
	}
}
