package com.pcdjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.repository.CandidatoRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private CandidatoRepository candidatoRepository;
	
//	private ResponseEntity<CandidatoAtualizadoDTO> autenticacaoTemporaria(@RequestBody FormLogin form, UriComponentsBuilder uriBuilder) {
//		
//	}
}
