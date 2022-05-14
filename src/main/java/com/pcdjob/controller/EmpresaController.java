package com.pcdjob.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.AreaDTO;
import com.pcdjob.controller.dto.EmpresaDTO;
import com.pcdjob.controller.dto.EmpresaSalvaDTO;
import com.pcdjob.controller.dto.EnderecoEmpresaDTO;
import com.pcdjob.controller.dto.response.ResponseEmailEmpresa;
import com.pcdjob.controller.dto.response.ResponseEnderecoEmpresa;
import com.pcdjob.controller.dto.response.ResponseTelefoneEmpresa;
import com.pcdjob.controller.form.AtualizarEmpresaForm;
import com.pcdjob.controller.form.EmpresaForm;
import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EnderecoEmpresa;
import com.pcdjob.model.empresa.TelefoneEmpresa;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.EnderecoEmpresaRepository;
import com.pcdjob.service.AreaAtuacaoService;
import com.pcdjob.service.EmpresaResponseService;
import com.pcdjob.service.EmpresaService;
import com.pcdjob.service.EnderecoService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EnderecoEmpresaRepository enderecoRepository;
	
	@Autowired
	private AreaAtuacaoService areaService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EmpresaResponseService empresaResponseService;
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar", produces = "application/json")
	@Transactional
	public ResponseEntity<EmpresaDTO> cadastrarEmpresa(@RequestBody EmpresaForm form, UriComponentsBuilder uriBuilder) {
		AreaAtuacao area = areaService.buscarAreaID(form.getAreaAtuacao());
		EmpresaEntity empresa = form.converter(area);
		EmpresaEntity empresaSalva = empresaRepository.save(empresa);
		empresaService.converterEmail(form.getEmail(), empresaSalva);
		empresaService.converterTelefone(empresaSalva, form.getTelefone());
		
		URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmpresaDTO(empresa));
	}
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/endereco/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EnderecoEmpresaDTO> cadastrarEnderecoEmpresa(@PathVariable Long id, @RequestBody EmpresaForm form, UriComponentsBuilder uriBuilder) {
		EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
		Cidade cidade = enderecoService.converterCidade(form.getCidade(), form.getSigla(), form.getEstado());
		EnderecoEmpresa endereco = form.converterEndereco(empresa, cidade);
		enderecoRepository.save(endereco);
		URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoEmpresaDTO(endereco));
	}
	
	@CrossOrigin
	@GetMapping(path = "/buscar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EmpresaSalvaDTO> buscarEmpresa(@PathVariable Long id) {
		EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
		if(empresa != null) {
			List<ResponseEmailEmpresa> responseEmail = empresaResponseService.converterEmail(empresa);
			List<ResponseTelefoneEmpresa> responseTelefone = empresaResponseService.converterTelefone(empresa);
			ResponseEnderecoEmpresa responseEndereco = empresaResponseService.converterEndereco(empresa);
			AreaDTO area = new AreaDTO(empresa.getAreaAtuacao());
			return ResponseEntity.ok(new EmpresaSalvaDTO(empresa, responseEmail, responseTelefone, responseEndereco, area));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<EmpresaSalvaDTO> listarEmpresa(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		List<EmpresaEntity> empresas = empresaRepository.findAll();
		List<EmpresaSalvaDTO> dtos = empresaResponseService.listarEmpresas(empresas);
		return empresaResponseService.paginarEmpresasDTO(dtos, paginacao);
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> atualizarEmpresa(@PathVariable Long id, @RequestBody AtualizarEmpresaForm form, UriComponentsBuilder uriBuilder) {
		try {
			EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
			AreaAtuacao area = areaService.buscarAreaID(form.getAreaAtuacao());
			Cidade cidade = enderecoService.converterCidade(form.getCidade(), form.getSigla(), form.getEstado());
			List<TelefoneEmpresa> telefones = empresaService.atualizarTelefones(form.getTelefone(), empresa);
			List<EmailEmpresa> emails = empresaService.atualizarEmails(form.getEmail(), empresa);
			EmpresaEntity empresaAtualizada = form.converter(empresa, area, telefones, emails, cidade);
			empresaRepository.save(empresaAtualizada);
			
			
			List<ResponseEmailEmpresa> responseEmail = empresaResponseService.converterEmail(empresaAtualizada);
			List<ResponseTelefoneEmpresa> responseTelefone = empresaResponseService.converterTelefone(empresaAtualizada);
			ResponseEnderecoEmpresa responseEndereco = empresaResponseService.converterEndereco(empresaAtualizada);
			AreaDTO responseArea = new AreaDTO(empresaAtualizada.getAreaAtuacao());
			URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri();
			return ResponseEntity.created(uri).body(new EmpresaSalvaDTO(empresaAtualizada, responseEmail, responseTelefone, responseEndereco, responseArea));
		} catch(Exception e) {
			e.getStackTrace();
		}
		return ResponseEntity.badRequest().build();
		
		
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarEmpresa(@PathVariable Long id) {
		EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
		if(empresa != null) {
			empresaService.deletarEmpresa(empresa);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
