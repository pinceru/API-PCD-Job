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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.AtualizarCandidatoDTO;
import com.pcdjob.controller.dto.AtualizarEnderecoCandidatoDTO;
import com.pcdjob.controller.dto.AtualizarExperienciaDTO;
import com.pcdjob.controller.dto.CandidatoAtualizadoDTO;
import com.pcdjob.controller.dto.CandidatoInseridoDTO;
import com.pcdjob.controller.dto.CursoCandidatoDTO;
import com.pcdjob.controller.dto.EnderecoCandidatoDTO;
import com.pcdjob.controller.dto.ExperienciaProfissionalDTO;
import com.pcdjob.controller.dto.InserirCandidatoDTO;
import com.pcdjob.controller.dto.InserirCursoDTO;
import com.pcdjob.controller.dto.InserirEnderecoCandidatoDTO;
import com.pcdjob.controller.dto.InserirExperienciaProfissionalDTO;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CursoCandidato;
import com.pcdjob.model.candidato.EnderecoCandidato;
import com.pcdjob.model.candidato.ExperienciaProfissional;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.CursoCandidatoRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.DeficienciaCandidatoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EmailCandidatoRepository;
import com.pcdjob.repository.EnderecoCandidatoRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.ExperienciaProfissionalRepository;
import com.pcdjob.repository.GeneroRepository;
import com.pcdjob.repository.TelefoneCandidatoRepository;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private EmailCandidatoRepository emailRepository;
	
	@Autowired
	private DeficienciaCandidatoRepository deficienciaCandidatoRepository;
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@Autowired
	private TelefoneCandidatoRepository telefoneRepository;
	
	@Autowired
	private ExperienciaProfissionalRepository experienciaProfissionalRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CursoCandidatoRepository cursoCandidatoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoCandidatoRepository enderecoRepository;
	
	@PostMapping(value= "/cadastrar")
	@Transactional
	public ResponseEntity<CandidatoInseridoDTO> cadastrarCandidato(@RequestBody InserirCandidatoDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		CandidatoEntity candidato = insercaoDTO.converter(generoRepository);
		CandidatoEntity candidatoSalvo = candidatoRepository.save(candidato);
		insercaoDTO.converterEmail(emailRepository, candidatoSalvo);
		
		URI uri = uriBuilder.path("/candidato/{id}")
				.buildAndExpand(candidatoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoInseridoDTO(candidatoSalvo));
	}

	@PutMapping("/atualizar/{id}")
	@Transactional 
	public ResponseEntity<CandidatoAtualizadoDTO> atualizarCadastroCandidato(@PathVariable Long id, @RequestBody AtualizarCandidatoDTO atualizacaoDTO, UriComponentsBuilder uriBuilder) {
		CandidatoEntity candidato = atualizacaoDTO.converter(id, candidatoRepository, generoRepository);
		atualizacaoDTO.converterDeficiencia(candidato, deficienciaRepository, deficienciaCandidatoRepository);
		atualizacaoDTO.converterTelefone(candidato, telefoneRepository);
		atualizacaoDTO.converterEmail(candidato, emailRepository);
		candidatoRepository.save(candidato);
		
		URI uri = uriBuilder.path("/candidato/{id}")
				.buildAndExpand(candidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoAtualizadoDTO(candidato));
	}
	
	@GetMapping("/listar")
	@Transactional
	public Page<CandidatoAtualizadoDTO> listarTodos(@PageableDefault(sort = "deficienciaCandidato", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<CandidatoEntity> candidatos = candidatoRepository.findAll(paginacao);
		return CandidatoAtualizadoDTO.converter(candidatos);
	}
	
	@GetMapping("/buscar/{id}")
	@Transactional
	public ResponseEntity<CandidatoAtualizadoDTO> buscarId(@PathVariable Long id) {
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(id);
		if(candidato.isPresent()) {
			return ResponseEntity.ok(new CandidatoAtualizadoDTO(candidato.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletarCandidato(@PathVariable Long id) {
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(id);
		if(candidato.isPresent()) {
			candidatoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping("/cadastrar/experiencia/{id}")
	@Transactional
	public ResponseEntity<ExperienciaProfissionalDTO> cadastrarExperiencia(@PathVariable Long id, @RequestBody InserirExperienciaProfissionalDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		ExperienciaProfissional experienciaProfissional = insercaoDTO.converter(id, candidatoRepository);
		experienciaProfissionalRepository.save(experienciaProfissional);
		
		URI uri = uriBuilder.path("/candidato/experiencia/{id}")
				.buildAndExpand(experienciaProfissional.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExperienciaProfissionalDTO(experienciaProfissional));
	}
	
	@GetMapping("/listar/experiencia/{id}")
	@Transactional
	public Page<ExperienciaProfissionalDTO> listarExperienciaProfissional(@PathVariable Long id, @PageableDefault(sort = "dataInicio", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(id);
		Page<ExperienciaProfissional> experiencias = experienciaProfissionalRepository.findByCandidato(candidato.get(), paginacao);
		return ExperienciaProfissionalDTO.converter(experiencias);
	}
	
	@PutMapping("/atualizar/experiencia/{id}")
	@Transactional
	public ResponseEntity<ExperienciaProfissionalDTO> atualizarExperienciaProfissional(@PathVariable Long id, @RequestBody AtualizarExperienciaDTO atualizacaoDTO, UriComponentsBuilder uriBuilder) {
		ExperienciaProfissional experienciaProfissional = atualizacaoDTO.converter(id, experienciaProfissionalRepository);
		experienciaProfissionalRepository.save(experienciaProfissional);
		
		URI uri = uriBuilder.path("/candidato/experiencia/{id}")
				.buildAndExpand(experienciaProfissional.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExperienciaProfissionalDTO(experienciaProfissional));
	}
	
	@DeleteMapping("/deletar/experiencia/{id}")
	@Transactional
	public ResponseEntity<?> deletarExperiencia(@PathVariable Long id) {
		Optional<ExperienciaProfissional> experiencia = experienciaProfissionalRepository.findById(id);
		if(experiencia.isPresent()) {
			experienciaProfissionalRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cadastrar/curso/{id}")
	@Transactional
	public ResponseEntity<CursoCandidatoDTO> cadastrarCursoCandidato(@PathVariable Long id, @RequestBody InserirCursoDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		CursoCandidato cursoCandidato = insercaoDTO.converter(id, candidatoRepository, cursoRepository);
		cursoCandidatoRepository.save(cursoCandidato);
		
		URI uri = uriBuilder.path("/candidato/curso/{id}")
				.buildAndExpand(cursoCandidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CursoCandidatoDTO(cursoCandidato));
	}
	
	@GetMapping("/listar/cursos/{id}")
	@Transactional
	public Page<CursoCandidatoDTO> listarCursosCandidato(@PathVariable Long id,  @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(id);
		Page<CursoCandidato> cursos = cursoCandidatoRepository.findByCandidato(candidato.get(), paginacao);
		return CursoCandidatoDTO.converter(cursos);
	}
	
	@DeleteMapping("/deletar/curso/{id}")
	@Transactional
	public ResponseEntity<?> deletarCurso(@PathVariable Long id) {
		Optional<CursoCandidato> curso = cursoCandidatoRepository.findById(id);
		if(curso.isPresent()) {
			cursoCandidatoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cadastrar/endereco/{id}")
	@Transactional
	public ResponseEntity<EnderecoCandidatoDTO> cadastrarEndereco(@PathVariable Long id, @RequestBody InserirEnderecoCandidatoDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		Estado estado = insercaoDTO.converterEstado(estadoRepository);
		Cidade cidade = insercaoDTO.converterCidade(cidadeRepository, estado);
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(id);
		EnderecoCandidato endereco = insercaoDTO.converter(candidato.get(), cidade);
		enderecoRepository.save(endereco);
		
		URI uri = uriBuilder.path("/candidato/endereco/{id}")
				.buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoCandidatoDTO(endereco));
	}
	
	@PutMapping("/atualizar/endereco/{id}")
	@Transactional
	public ResponseEntity<EnderecoCandidatoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody AtualizarEnderecoCandidatoDTO atualizacaoDTO, UriComponentsBuilder uriBuilder) {
		EnderecoCandidato endereco = atualizacaoDTO.converter(id, enderecoRepository, estadoRepository, cidadeRepository);
		enderecoRepository.save(endereco);
		
		URI uri = uriBuilder.path("/candidato/endereco/{id}")
				.buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoCandidatoDTO(endereco));
	}
}
