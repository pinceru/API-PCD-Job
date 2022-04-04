package com.pcdjob.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.DeficienciaDTO;
import com.pcdjob.controller.dto.TipoDeficienciaDTO;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.TipoDeficiencia;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.TipoDeficienciaRepository;

@RestController
@RequestMapping("/deficiencia")
public class DeficienciaController {

	@Autowired
	private TipoDeficienciaRepository tipoRepository;
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@GetMapping("/listar/tipo")
	@Transactional
	public Page<TipoDeficienciaDTO> listarTipoDeficiencia(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<TipoDeficiencia> tipo = tipoRepository.findAll(paginacao);
		return TipoDeficienciaDTO.converter(tipo);
	}
	
	@GetMapping("/listar/{id}")
	@Transactional
	public Page<DeficienciaDTO> listarDeficienciaPorTipo(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Optional<TipoDeficiencia> tipo = tipoRepository.findById(id);
		Page<Deficiencia> deficiencias = deficienciaRepository.findByTipoDeficiencia(tipo.get(), paginacao);
		return DeficienciaDTO.converter(deficiencias);
	}
}