package com.pcdjob.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.CandidatoAtualizadoDTO;
import com.pcdjob.controller.dto.EmpresaSalvaDTO;
import com.pcdjob.controller.dto.FormLogin;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.EmpresaRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@CrossOrigin
	@PostMapping(path = "/candidato", produces = "application/json")
	private ResponseEntity<?> autenticacaoTemporariaCandidato(@RequestBody FormLogin form, UriComponentsBuilder uriBuilder) {
		Optional<CandidatoEntity> candidato = candidatoRepository.findByEmailCandidatoEmail(form.getLogin());
		if(candidato.isPresent()) {
			if(form.autenticaCandidato(candidato.get())) {
				URI uri = uriBuilder.path("/candidato/{id}")
						.buildAndExpand(candidato.get().getId()).toUri();
				return ResponseEntity.created(uri).body(new CandidatoAtualizadoDTO(candidato.get()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/empresa", produces = "application/json")
	private ResponseEntity<?> autenticacaoTemporariaEmpresa(@RequestBody FormLogin form, UriComponentsBuilder uriBuilder) {
		Optional<EmpresaEntity> empresa = empresaRepository.findByEmailEmpresaEmail(form.getLogin());
		if(empresa.isPresent()) {
			if(form.autenticaEmpresa(empresa.get())) {
				URI uri = uriBuilder.path("/empresa/{id}")
						.buildAndExpand(empresa.get().getId()).toUri();
				return ResponseEntity.created(uri).body(new EmpresaSalvaDTO(empresa.get()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
