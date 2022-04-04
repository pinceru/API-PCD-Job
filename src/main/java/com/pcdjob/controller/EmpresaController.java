package com.pcdjob.controller;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.EmpresaDTO;
import com.pcdjob.controller.dto.InserirEmpresaDTO;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.repository.AreaAtuacaoRepository;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.TelefoneEmpresaRepository;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmailEmpresaRepository emailRepository;
	
	@Autowired
	private TelefoneEmpresaRepository telefoneRepository;
	
	@Autowired
	private AreaAtuacaoRepository areaRepository;
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<EmpresaDTO> cadastrarEmpresa(@RequestBody InserirEmpresaDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		EmpresaEntity empresa = insercaoDTO.converter(areaRepository);
		EmpresaEntity empresaSalva = empresaRepository.save(empresa);
		insercaoDTO.converterEmail(empresaSalva, emailRepository);
		insercaoDTO.converterTelefone(empresaSalva, telefoneRepository);
		
		
		URI uri = uriBuilder.path("/empresa/{id}")
				.buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmpresaDTO(empresa));
	}
	
}
