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

import com.pcdjob.controller.dto.CandidatoAtualizadoDTO;
import com.pcdjob.controller.dto.CandidatoInseridoDTO;
import com.pcdjob.controller.dto.CursoCandidatoDTO;
import com.pcdjob.controller.dto.EnderecoCandidatoDTO;
import com.pcdjob.controller.dto.ExperienciaProfissionalDTO;
import com.pcdjob.controller.dto.response.ResponseCursoCandidato;
import com.pcdjob.controller.dto.response.ResponseDeficiencia;
import com.pcdjob.controller.dto.response.ResponseEmailCandidato;
import com.pcdjob.controller.dto.response.ResponseExperienciaProfissional;
import com.pcdjob.controller.dto.response.ResponseTelefoneCandidato;
import com.pcdjob.controller.form.AtualizarCandidatoForm;
import com.pcdjob.controller.form.AtualizarEnderecoCandidatoForm;
import com.pcdjob.controller.form.AtualizarExperienciaForm;
import com.pcdjob.controller.form.CandidatoForm;
import com.pcdjob.controller.form.CursoForm;
import com.pcdjob.controller.form.EnderecoCandidatoForm;
import com.pcdjob.controller.form.ExperienciaProfissionalForm;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.Curso;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CursoCandidato;
import com.pcdjob.model.candidato.EnderecoCandidato;
import com.pcdjob.model.candidato.ExperienciaProfissional;
import com.pcdjob.model.candidato.Genero;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.CursoCandidatoRepository;
import com.pcdjob.repository.EnderecoCandidatoRepository;
import com.pcdjob.repository.ExperienciaProfissionalRepository;
import com.pcdjob.service.CandidatoResponseService;
import com.pcdjob.service.CandidatoService;
import com.pcdjob.service.CursoCandidatoService;
import com.pcdjob.service.CursoService;
import com.pcdjob.service.DeficienciaService;
import com.pcdjob.service.EnderecoCandidatoService;
import com.pcdjob.service.EnderecoService;
import com.pcdjob.service.ExperienciaProfissionalService;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private ExperienciaProfissionalRepository experienciaProfissionalRepository;
	
	@Autowired
	private CursoCandidatoRepository cursoCandidatoRepository;
	
	@Autowired
	private EnderecoCandidatoRepository enderecoRepository;
	
	@Autowired
	private CandidatoService candidatoService;
	
	@Autowired
	private DeficienciaService deficienciaService;
	
	@Autowired
	private CandidatoResponseService candidatoReponseService;
	
	@Autowired
	private ExperienciaProfissionalService experienciaService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CursoCandidatoService cursoCandidatoService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EnderecoCandidatoService enderecoCandidatoService;
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar", produces = "application/json")
	@Transactional
	public ResponseEntity<CandidatoInseridoDTO> cadastrarCandidato(@RequestBody CandidatoForm form, UriComponentsBuilder uriBuilder) {
		Genero genero = candidatoService.recuperarGenero(form.getGenero());
		CandidatoEntity candidato = form.converter(genero);
		CandidatoEntity candidatoSalvo = candidatoRepository.save(candidato);
		candidatoService.converterEmail(candidatoSalvo, form.getEmail());
		
		URI uri = uriBuilder.path("/candidato/{id}").buildAndExpand(candidatoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoInseridoDTO(candidatoSalvo));
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/{id}", produces = "application/json")
	@Transactional 
	public ResponseEntity<CandidatoAtualizadoDTO> atualizarCadastroCandidato(@PathVariable Long id, @RequestBody AtualizarCandidatoForm form, UriComponentsBuilder uriBuilder) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		Genero genero = candidatoService.recuperarGenero(form.getGenero());
		CandidatoEntity candidatoAtualizado = form.converter(candidato, genero);
		deficienciaService.converterDeficiencias(candidatoAtualizado, form.getDeficiencia());
		candidatoService.salvarTelefones(candidatoAtualizado, form.getTelefone());
		candidatoService.salvarEmails(candidatoAtualizado, form.getEmail());
		CandidatoEntity candidatoSalvo = candidatoRepository.save(candidatoAtualizado);
		
		List<ResponseExperienciaProfissional> responseExperiencia = candidatoReponseService.converterExperienciaProfissional(candidatoSalvo);
		List<ResponseDeficiencia> responseDeficiencia = candidatoReponseService.converterDeficienciaCandidato(candidatoSalvo);
		List<ResponseCursoCandidato> responseCurso = candidatoReponseService.converterCursoCandidato(candidatoSalvo);
		List<ResponseTelefoneCandidato> responseTelefone = candidatoReponseService.converterTelefoneCandidato(candidatoSalvo);
		List<ResponseEmailCandidato> responseEmail = candidatoReponseService.converterEmailCandidato(candidatoSalvo);
		
		URI uri = uriBuilder.path("/candidato/{id}").buildAndExpand(candidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoAtualizadoDTO(candidato, responseEmail, responseTelefone, responseDeficiencia, responseExperiencia, responseCurso));
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<CandidatoAtualizadoDTO> listarTodos(@PageableDefault(sort = "deficienciaCandidato", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		List<CandidatoEntity> candidatos = candidatoRepository.findAll();
		List<CandidatoAtualizadoDTO> dtos = candidatoReponseService.listarCandidatos(candidatos);
		return candidatoReponseService.paginarCandidatosDTO(dtos, paginacao);
	}
	
	@CrossOrigin
	@GetMapping(path = "/buscar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<CandidatoAtualizadoDTO> buscarId(@PathVariable Long id) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		List<ResponseExperienciaProfissional> responseExperiencia = candidatoReponseService.converterExperienciaProfissional(candidato);
		List<ResponseDeficiencia> responseDeficiencia = candidatoReponseService.converterDeficienciaCandidato(candidato);
		List<ResponseCursoCandidato> responseCurso = candidatoReponseService.converterCursoCandidato(candidato);
		List<ResponseTelefoneCandidato> responseTelefone = candidatoReponseService.converterTelefoneCandidato(candidato);
		List<ResponseEmailCandidato> responseEmail = candidatoReponseService.converterEmailCandidato(candidato);
		
		if(candidato != null) {
			return ResponseEntity.ok(new CandidatoAtualizadoDTO(candidato, responseEmail, responseTelefone, responseDeficiencia, responseExperiencia, responseCurso));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarCandidato(@PathVariable Long id) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		if(candidato != null) {
			candidatoService.deletarCandidato(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/experiencia/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<ExperienciaProfissionalDTO> cadastrarExperiencia(@PathVariable Long id, @RequestBody ExperienciaProfissionalForm form, UriComponentsBuilder uriBuilder) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		ExperienciaProfissional experienciaProfissional = form.converter(candidato);
		experienciaProfissionalRepository.save(experienciaProfissional);
		
		URI uri = uriBuilder.path("/candidato/experiencia/{id}").buildAndExpand(experienciaProfissional.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExperienciaProfissionalDTO(experienciaProfissional));
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/experiencia/{id}", produces = "application/json")
	@Transactional
	public Page<ExperienciaProfissionalDTO> listarExperienciaProfissional(@PathVariable Long id, @PageableDefault(sort = "dataInicio", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		Page<ExperienciaProfissional> experiencias = experienciaProfissionalRepository.findByCandidato(candidato, paginacao);
		return ExperienciaProfissionalDTO.converter(experiencias);
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/experiencia/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<ExperienciaProfissionalDTO> atualizarExperienciaProfissional(@PathVariable Long id, @RequestBody AtualizarExperienciaForm form, UriComponentsBuilder uriBuilder) {
		ExperienciaProfissional experiencia = experienciaService.buscarExperienciaID(id);
		ExperienciaProfissional experienciaAtualizada = form.converter(experiencia);
		experienciaProfissionalRepository.save(experienciaAtualizada);
		
		URI uri = uriBuilder.path("/candidato/experiencia/{id}").buildAndExpand(experienciaAtualizada.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExperienciaProfissionalDTO(experienciaAtualizada));
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/experiencia/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarExperiencia(@PathVariable Long id) {
		ExperienciaProfissional experiencia = experienciaService.buscarExperienciaID(id);
		if(experiencia != null) {
			experienciaService.deletarExperienciaProfissional(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/curso/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<CursoCandidatoDTO> cadastrarCursoCandidato(@PathVariable Long id, @RequestBody CursoForm form, UriComponentsBuilder uriBuilder) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		Curso curso = cursoService.buscarCursoID(form.getCurso());
		CursoCandidato cursoCandidato = form.converter(candidato, curso);
		cursoCandidatoRepository.save(cursoCandidato);
		
		URI uri = uriBuilder.path("/candidato/curso/{id}").buildAndExpand(cursoCandidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CursoCandidatoDTO(cursoCandidato));
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/cursos/{id}", produces = "application/json")
	@Transactional
	public Page<CursoCandidatoDTO> listarCursosCandidato(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		Page<CursoCandidato> cursos = cursoCandidatoService.paginarCursosCandidato(paginacao, candidato);
		return CursoCandidatoDTO.converter(cursos);
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/curso/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarCurso(@PathVariable Long id) {
		CursoCandidato curso = cursoCandidatoService.buscarCursoCandidatoID(id);
		if(curso != null) {
			cursoCandidatoService.deletarCursoCandidato(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/endereco/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EnderecoCandidatoDTO> cadastrarEndereco(@PathVariable Long id, @RequestBody EnderecoCandidatoForm form, UriComponentsBuilder uriBuilder) {
		Cidade cidade = enderecoService.converterCidade(form.getCidade(), form.getSigla(), form.getEstado());
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		EnderecoCandidato endereco = form.converter(candidato, cidade);
		enderecoRepository.save(endereco);
		
		URI uri = uriBuilder.path("/candidato/endereco/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoCandidatoDTO(endereco));
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/endereco/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<EnderecoCandidatoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody AtualizarEnderecoCandidatoForm form, UriComponentsBuilder uriBuilder) {
		Cidade cidade = enderecoService.converterCidade(form.getCidade(), form.getSigla(), form.getEstado());
		EnderecoCandidato enderecoCandidato = enderecoCandidatoService.buscarEnderecoCandidatoID(id);
		EnderecoCandidato endereco = form.converter(enderecoCandidato, cidade);
		enderecoRepository.save(endereco);
		
		URI uri = uriBuilder.path("/candidato/endereco/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoCandidatoDTO(endereco));
	}
}
