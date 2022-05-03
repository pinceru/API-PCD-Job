package com.pcdjob.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.EmailForm;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.EmailEmpresaRepository;

@RestController
@RequestMapping("email") 
public class EmailController {
	
	@Autowired
	private EmailCandidatoRepository emailCandidatoRepository;
	
	@Autowired
	private EmailEmpresaRepository emailEmpresaRepository;
	
	@CrossOrigin
	@PostMapping(path = "/candidato", produces = "application/json")
	public ResponseEntity<?> buscaEmailCandidato(@RequestBody EmailForm email){
		String strEmail = email.converter();
		Optional<EmailCandidato> emailObj = emailCandidatoRepository.findByEmail(strEmail);
		if(emailObj.isPresent()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/empresa", produces = "application/json")
	public ResponseEntity<?> buscaEmailEmpresa(@RequestBody EmailForm email){
		String strEmail = email.converter();
		Optional<EmailEmpresa> emailObj = emailEmpresaRepository.findByEmail(strEmail);
		if(emailObj.isPresent()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok().build();
		}
	}
}
