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

import com.pcdjob.controller.dto.CandidatoInseridoDTO;
import com.pcdjob.controller.dto.EmpresaDTO;
import com.pcdjob.controller.form.LoginForm;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.service.AuthService;
import com.pcdjob.service.helper.Verificar;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@CrossOrigin
	@PostMapping(path = "/candidato", produces = "application/json")
	private ResponseEntity<?> autenticacaoTemporariaCandidato(@RequestBody LoginForm form, UriComponentsBuilder uriBuilder) {
		Optional<CandidatoEntity> candidato = authService.buscarCandidatoEmail(form.getLogin());
		if(candidato.isPresent()) {
			if(authService.autenticaCandidato(candidato.get(), form.getLogin(), form.getSenha())) {
				URI uri = uriBuilder.path("/candidato/{id}").buildAndExpand(candidato.get().getId()).toUri();
				return ResponseEntity.created(uri).body(new CandidatoInseridoDTO(candidato.get()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/empresa", produces = "application/json")
	private ResponseEntity<?> autenticacaoTemporariaEmpresa(@RequestBody LoginForm form, UriComponentsBuilder uriBuilder) {
		Optional<EmpresaEntity> empresa = authService.buscarEmpresaEmail(form.getLogin());
		if(Verificar.verificarOptional(empresa)) {
			if(authService.autenticaEmpresa(empresa.get(), form.getLogin(), form.getSenha())) {
				URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.get().getId()).toUri();
				return ResponseEntity.created(uri).body(new EmpresaDTO(empresa.get()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
