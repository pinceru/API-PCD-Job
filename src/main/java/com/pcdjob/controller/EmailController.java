package com.pcdjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.form.EmailForm;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.service.EmailCadastroService;

@RestController
@RequestMapping("email") 
public class EmailController {
	
	@Autowired
	private EmailCadastroService service;
	
	@CrossOrigin
	@PostMapping(path = "/candidato", produces = "application/json")
	public ResponseEntity<?> buscaEmailCandidato(@RequestBody EmailForm email){
		String strEmail = email.converter();
		EmailCandidato emailObj = service.verificarEmailCandidato(strEmail);
		if(emailObj != null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/empresa", produces = "application/json")
	public ResponseEntity<?> buscaEmailEmpresa(@RequestBody EmailForm email){
		String strEmail = email.converter();
		EmailEmpresa emailObj = service.verificarEmailEmpresa(strEmail);
		if(emailObj != null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok().build();
		}
	}
}
