package com.pcdjob.controller;

import java.net.URI;
import java.util.Optional;

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

import com.pcdjob.controller.dto.AtualizarEmpresaDTO;
import com.pcdjob.controller.dto.EmpresaDTO;
import com.pcdjob.controller.dto.EmpresaSalvaDTO;
import com.pcdjob.controller.dto.EnderecoEmpresaDTO;
import com.pcdjob.controller.dto.InserirEmpresaDTO;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EnderecoEmpresa;
import com.pcdjob.repository.AreaAtuacaoRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.EnderecoEmpresaRepository;
import com.pcdjob.repository.EstadoRepository;
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
	
	@Autowired
	private EnderecoEmpresaRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar", produces = "application/json")
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
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/endereco/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EnderecoEmpresaDTO> cadastrarEnderecoEmpresa(@PathVariable Long id, @RequestBody InserirEmpresaDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		EmpresaEntity empresa = empresaRepository.getOne(id);
		EnderecoEmpresa endereco = insercaoDTO.converterEndereco(empresa, enderecoRepository, cidadeRepository, estadoRepository);
		
		URI uri = uriBuilder.path("/empresa/{id}")
				.buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoEmpresaDTO(endereco));
	}
	
	@CrossOrigin
	@GetMapping(path = "/buscar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EmpresaSalvaDTO> buscarEmpresa(@PathVariable Long id) {
		Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
		if(empresa.isPresent()) {
			return ResponseEntity.ok(new EmpresaSalvaDTO(empresa.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<EmpresaSalvaDTO> listarEmpresa(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<EmpresaEntity> empresas = empresaRepository.findAll(paginacao);
		return EmpresaSalvaDTO.converter(empresas);
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EmpresaSalvaDTO> atualizarEmpresa(@PathVariable Long id, @RequestBody AtualizarEmpresaDTO atualizacaoDTO, UriComponentsBuilder uriBuilder) {
		EmpresaEntity empresa = atualizacaoDTO.converter(id, empresaRepository, areaRepository, telefoneRepository, emailRepository, estadoRepository, cidadeRepository);
		empresaRepository.save(empresa);
		
		URI uri = uriBuilder.path("/empresa/{id}")
				.buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmpresaSalvaDTO(empresa));
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarEmpresa(@PathVariable Long id) {
		Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
		if(empresa.isPresent()) {
			empresaRepository.delete(empresa.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
