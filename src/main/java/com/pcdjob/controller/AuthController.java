package com.pcdjob.controller;

import java.net.URI;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.CandidatoInseridoDTO;
import com.pcdjob.controller.dto.EmpresaDTO;
import com.pcdjob.controller.dto.response.ResponseEmailCandidato;
import com.pcdjob.controller.dto.response.ResponseEmailEmpresa;
import com.pcdjob.controller.form.AtualizarSenhaCandidatoForm;
import com.pcdjob.controller.form.AtualizarSenhaEmpresaForm;
import com.pcdjob.controller.form.LoginForm;
import com.pcdjob.controller.form.SenhaForm;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.service.AuthService;
import com.pcdjob.service.CandidatoService;
import com.pcdjob.service.EmailService;
import com.pcdjob.service.EmpresaService;
import com.pcdjob.service.SenhaService;
import com.pcdjob.service.helper.Verificar;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private CandidatoService candidatoService;
	
	@Autowired
	private SenhaService senhaService;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
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
	
	@CrossOrigin
	@PostMapping(path = "/enviar/email", produces = "application/json")
	public ResponseEntity<?> recuperarSenhaEmpresa(@RequestBody SenhaForm form) {
		String email = form.converter();
		Optional<EmailEmpresa> optionalEmpresa = empresaService.verificarEmail(email);
		Optional<EmailCandidato> optionalCandidato = candidatoService.verificarEmail(email);
		
		if(optionalEmpresa.isPresent() != true && optionalCandidato.isPresent() != true) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			Random random = new Random();
			Integer numero = random.nextInt(1000) * 9;
			String conteudo = "O seu código para recuperar senha é: " + numero + ".";
			emailService.enviarEmail("Recuperar senha PCDJob.", email, conteudo);
			senhaService.salvarRelacao(optionalEmpresa, optionalCandidato, numero);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			System.out.println("Não foi possível realizar o cadastro devido a um exception " + e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/verificar/codigo/candidato", produces = "application/json")
	public ResponseEntity<?> verificarCodigoCandidato(@RequestBody SenhaForm form, UriComponentsBuilder uriBuilder) {
		Integer codigo = form.getCodigo();
		try {
			EmailCandidato emailCandidato = senhaService.compararCodigoCandidato(codigo);
			URI uri = uriBuilder.path("/candidato/{id}").buildAndExpand(emailCandidato.getId()).toUri();
			return ResponseEntity.created(uri).body(new ResponseEmailCandidato(emailCandidato.getId(), emailCandidato.getEmail()));
		} catch(Exception e) {
			System.out.println("Não foi possível realizar o cadastro devido a um exception " + e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/verificar/codigo/empresa", produces = "application/json")
	public ResponseEntity<?> verificarCodigoEmpresa(@RequestBody SenhaForm form, UriComponentsBuilder uriBuilder) {
		Integer codigo = form.getCodigo();
		try {
			EmailEmpresa emailEmpresa = senhaService.compararCodigoEmpresa(codigo);
			URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(emailEmpresa.getId()).toUri();
			return ResponseEntity.created(uri).body(new ResponseEmailEmpresa(emailEmpresa.getId(), emailEmpresa.getEmail()));
		} catch(Exception e) {
			System.out.println("Não foi possível realizar o cadastro devido a um exception " + e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PutMapping(path = "/senha/candidato", produces = "application/json")
	public ResponseEntity<?> alterarSenhaCandidato(@RequestParam(required = true) String email, @RequestBody AtualizarSenhaCandidatoForm form, UriComponentsBuilder uriBuilder) {
		try {
			CandidatoEntity candidato = candidatoService.buscarCandidatoEmail(email);
			String senha = form.converter();
			CandidatoEntity candidatoAtualizado = form.atualizarSenha(candidato, new BCryptPasswordEncoder().encode(senha));
			candidatoRepository.save(candidatoAtualizado);
			URI uri = uriBuilder.path("/candidato/{id}").buildAndExpand(candidatoAtualizado.getId()).toUri();
			return ResponseEntity.created(uri).body(new CandidatoInseridoDTO(candidatoAtualizado));
		} catch(RuntimeException e) {
			System.out.println("Não foi possível realizar o cadastro devido a um exception " + e);
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PutMapping(path = "/senha/empresa", produces = "application/json")
	public ResponseEntity<?> alterarSenhaEmpresa(@RequestParam(required = true) String email, @RequestBody AtualizarSenhaEmpresaForm form, UriComponentsBuilder uriBuilder) {
		try {
			EmpresaEntity empresa = empresaService.buscarEmpresaEmail(email);
			String senha = form.converter();
			EmpresaEntity empresaAtualizada = form.converter(empresa, new BCryptPasswordEncoder().encode(senha));
			empresaRepository.save(empresaAtualizada);
			URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresaAtualizada.getId()).toUri();
			return ResponseEntity.created(uri).body(new EmpresaDTO(empresaAtualizada));
		} catch(Exception e) {
			System.out.println("Não foi possível realizar o cadastro devido a um exception " + e);
			return ResponseEntity.notFound().build();
		}
	}
}