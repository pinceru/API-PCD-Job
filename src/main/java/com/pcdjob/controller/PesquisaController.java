package com.pcdjob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.CidadeDTO;
import com.pcdjob.controller.dto.EstadoDTO;
import com.pcdjob.controller.dto.VagaSalvaDTO;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.service.EnderecoService;
import com.pcdjob.service.PesquisaService;
import com.pcdjob.service.VagaResponseService;

@RestController 
@RequestMapping("/pesquisa")
public class PesquisaController {
	
	@Autowired 
	private PesquisaService pesquisaService;
	
	@Autowired
	private VagaResponseService vagaResponseService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@CrossOrigin
	@GetMapping(path = "/", produces = "application/json")
	public Page<VagaSalvaDTO> pesquisarCandidato(@RequestParam(required = true) String palavra, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		List<VagaEntity> vagas = pesquisaService.filtraPesquisa(palavra);
		List<VagaSalvaDTO> dtos = vagaResponseService.listarVagas(vagas);
		return vagaResponseService.paginarVagasDTO(dtos, paginacao);
	}
	
	@CrossOrigin
	@GetMapping(path = "/estado", produces = "application/json")
	public Page<EstadoDTO> listarEstados(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Estado> estados = enderecoService.listarTodosEstados(paginacao);
		return EstadoDTO.converter(estados);
	}
	
	@CrossOrigin
	@GetMapping(path = "/cidade/{id}", produces = "application/json")
	public Page<CidadeDTO> filtrarCidades(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Estado estado = enderecoService.buscarEstadoID(id);
		Page<Cidade> cidade = enderecoService.buscarCidadesEstado(estado, paginacao);
		return CidadeDTO.converter(cidade);
	}
	
}
