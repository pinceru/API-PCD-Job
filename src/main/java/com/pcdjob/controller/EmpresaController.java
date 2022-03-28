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
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmailEmpresaRepository emailRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<EmpresaDTO> cadastrarEmpresa(@RequestBody InserirEmpresaDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		EmpresaEntity empresa = insercaoDTO.converter();
		EmpresaEntity empresaSalva = empresaRepository.save(empresa);
		EmailEmpresa emailEmpresa =  insercaoDTO.converterEmail(empresaRepository, empresaSalva);
		emailRepository.save(emailEmpresa);
		
		URI uri = uriBuilder.path("/empresa/{id}")
				.buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmpresaDTO(empresa));
	}
}
