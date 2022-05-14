package com.pcdjob.controller;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.BeneficioDTO;
import com.pcdjob.controller.dto.CandidatoAtualizadoDTO;
import com.pcdjob.controller.dto.CandidatoVagaDTO;
import com.pcdjob.controller.dto.ContratoDTO;
import com.pcdjob.controller.dto.StatusDTO;
import com.pcdjob.controller.dto.SuporteDTO;
import com.pcdjob.controller.dto.VagaDTO;
import com.pcdjob.controller.dto.VagaSalvaDTO;
import com.pcdjob.controller.dto.response.ResponseBeneficio;
import com.pcdjob.controller.dto.response.ResponseDeficiencia;
import com.pcdjob.controller.dto.response.ResponseEmpresa;
import com.pcdjob.controller.dto.response.ResponseFormacao;
import com.pcdjob.controller.dto.response.ResponseHorario;
import com.pcdjob.controller.dto.response.ResponseLocalTrabalho;
import com.pcdjob.controller.dto.response.ResponseSalario;
import com.pcdjob.controller.dto.response.ResponseSuporte;
import com.pcdjob.controller.dto.response.ResponseTipoContrato;
import com.pcdjob.controller.form.AtualizarVagaForm;
import com.pcdjob.controller.form.VagaForm;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.Beneficio;
import com.pcdjob.model.vaga.Horario;
import com.pcdjob.model.vaga.LocalTrabalho;
import com.pcdjob.model.vaga.Salario;
import com.pcdjob.model.vaga.StatusVaga;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaCandidato;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.BeneficioRepository;
import com.pcdjob.repository.LocalTrabalhoRepository;
import com.pcdjob.repository.StatusRepository;
import com.pcdjob.repository.TipoContratoRepository;
import com.pcdjob.repository.VagaCandidatoRepository;
import com.pcdjob.repository.VagaRepository;
import com.pcdjob.service.CandidatoResponseService;
import com.pcdjob.service.CandidatoService;
import com.pcdjob.service.CandidatoVagaService;
import com.pcdjob.service.ContratoService;
import com.pcdjob.service.EmpresaService;
import com.pcdjob.service.EnderecoService;
import com.pcdjob.service.PesquisaService;
import com.pcdjob.service.SuporteService;
import com.pcdjob.service.VagaResponseService;
import com.pcdjob.service.VagaService;

@RestController
@RequestMapping("/vaga")
public class VagaController {

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private TipoContratoRepository tipoContratoRepository;
	
	@Autowired
	private BeneficioRepository beneficioRepository;
	
	@Autowired
	private LocalTrabalhoRepository localRepository;
	
	@Autowired
	private VagaCandidatoRepository vagaCandidatoRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private VagaService vagaService;
	
	@Autowired
	private CandidatoVagaService candidatoVagaService;
	
	@Autowired
	private CandidatoResponseService candidatoResponseService;
	
	@Autowired
	private VagaResponseService vagaResponseService;
	
	@Autowired
	private CandidatoService candidatoService;
	
	@Autowired
	private SuporteService suporteService;
	
	@Autowired
	private PesquisaService pesquisaService;
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaDTO> cadastrarVaga(@PathVariable Long id, @RequestBody VagaForm form, UriComponentsBuilder uriBuilder) {
		EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
		TipoContrato contrato = contratoService.buscarTipoContratoID(form.getTipoContrato());
		Cidade cidade = enderecoService.converterCidade(form.getCidade(), form.getSigla(), form.getEstado());
		LocalTrabalho localTrabalho = form.converterLocalTrabalho(cidade);
		LocalTrabalho localSalvo = localRepository.save(localTrabalho);
		Horario horario = vagaService.converterHorario(form.getHorarioInicio(), form.getHorarioSaida(), form.getStatusHorario());
		Salario salario = vagaService.converterSalario(form.getSalario(), form.getStatusSalario());
		VagaEntity vaga = form.converter(empresa, localSalvo, contrato, horario, salario);
		VagaEntity vagaSalva = vagaRepository.save(vaga);
		
		vagaService.adicionarRelacionamentosVaga(vagaSalva, form);
		
		URI uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vagaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(new VagaDTO(vagaSalva));
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarTodasVagas(@RequestParam(required = false) Long idDeficiencia,
			@RequestParam(required = false) Long idSuporte, @RequestParam(required = false) Long idEstado, @RequestParam(required = false) Long idCidade,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		List<VagaEntity> vagas = new ArrayList<>();
		if(idDeficiencia == null && idSuporte == null && idCidade == null && idEstado == null) {
			vagas = vagaRepository.findAll();
		} else {
			List<VagaEntity> vagasDeficiencia = pesquisaService.filtrarPorDeficiencia(idDeficiencia);
			List<VagaEntity> vagasSuporte = pesquisaService.filtrarPorSuporte(idSuporte);
			if(idCidade != null) {
				vagas = pesquisaService.filtrarPorCidade(idCidade);
			} else if(idEstado != null){
				vagas = pesquisaService.filtrarPorEstado(idEstado);
			}
			vagas.addAll(vagasDeficiencia);
			vagas.addAll(vagasSuporte);
		}
		
		List<VagaSalvaDTO> dtos = vagaResponseService.listarVagas(vagas);
		return vagaResponseService.paginarVagasDTO(dtos, paginacao);
	}
	
	@CrossOrigin
	@GetMapping(path = "/buscar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaSalvaDTO> buscarVaga(@PathVariable Long id) {
		VagaEntity vaga = vagaService.buscarVagaID(id);
		if(vaga != null) {
			List<ResponseSuporte> suportes = vagaResponseService.converterSuportes(vaga);
			List<ResponseBeneficio> beneficios = vagaResponseService.converterBeneficio(vaga);
			List<ResponseDeficiencia> deficiencias = vagaResponseService.converterDeficiencia(vaga);
			List<ResponseFormacao> formacao = vagaResponseService.converterFormacao(vaga);
			ResponseEmpresa empresa = vagaResponseService.converterEmpresa(vaga);
			ResponseHorario horario = vagaResponseService.converterHorario(vaga);
			ResponseSalario salario = vagaResponseService.converterSalario(vaga);
			ResponseTipoContrato contrato = vagaResponseService.converterTipoContrato(vaga);
			ResponseLocalTrabalho localTrabalho = vagaResponseService.converterLocal(vaga);
			
			return ResponseEntity.ok(new VagaSalvaDTO(vaga, localTrabalho, horario, contrato, salario, suportes, beneficios, formacao, deficiencias, empresa));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaSalvaDTO> atualizarVaga(@PathVariable Long id, @RequestBody AtualizarVagaForm form, UriComponentsBuilder uriBuilder) {
		VagaEntity vaga = vagaService.buscarVagaID(id);
		System.out.println("A vaga é:" + vaga.getTitulo());
		Salario salario = vagaService.converterSalario(form.getSalario(), form.getStatusSalario());
		TipoContrato tipoContrato = contratoService.buscarTipoContratoID(form.getTipoContrato());
		Horario horario = vagaService.converterHorario(form.getHorarioInicio(), form.getHorarioSaida(), form.getStatusHorario());
		VagaEntity vagaAtualizada = form.converter(vaga, salario, tipoContrato, horario);
		
		vagaService.atualizarRelacionamentosVaga(vagaAtualizada, form);
		VagaEntity vagaSalva = vagaRepository.save(vagaAtualizada);
		
		List<ResponseSuporte> suportesResponse = vagaResponseService.converterSuportes(vagaSalva);
		List<ResponseBeneficio> beneficiosResponse = vagaResponseService.converterBeneficio(vagaSalva);
		List<ResponseDeficiencia> deficienciasResponse = vagaResponseService.converterDeficiencia(vagaSalva);
		List<ResponseFormacao> formacaoResponse = vagaResponseService.converterFormacao(vagaSalva);
		ResponseEmpresa empresaResponse = vagaResponseService.converterEmpresa(vagaSalva);
		ResponseHorario horarioResponse = vagaResponseService.converterHorario(vagaSalva);
		ResponseSalario salarioResponse = vagaResponseService.converterSalario(vagaSalva);
		ResponseTipoContrato contratoResponse = vagaResponseService.converterTipoContrato(vagaSalva);
		ResponseLocalTrabalho localTrabalhoResponse = vagaResponseService.converterLocal(vagaSalva);
		
		URI uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vagaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(new VagaSalvaDTO(vagaSalva, localTrabalhoResponse, horarioResponse, 
				contratoResponse, salarioResponse, suportesResponse, beneficiosResponse, formacaoResponse, deficienciasResponse, empresaResponse));
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarVaga(@PathVariable Long id) {
		VagaEntity vaga = vagaService.buscarVagaID(id);
		if(vaga != null) {
			vagaService.deletarVaga(vaga);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/empresa/{id}", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarVagasEmresa(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
		if(empresa != null) {
			List<VagaEntity> vagas = vagaRepository.findAllByEmpresa(empresa);
			List<VagaSalvaDTO> dtos = vagaResponseService.listarVagas(vagas);
			return vagaResponseService.paginarVagasDTO(dtos, paginacao);
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	@PostMapping(path = "/candidatar", produces = "application/json")
	@Transactional
	public ResponseEntity<CandidatoVagaDTO> candidatarVaga(@RequestParam(required = true) Long idCandidato, @RequestParam(required = true) Long idStatus, @RequestParam(required = true) Long idVaga, 
			UriComponentsBuilder uriBuilder) {
		VagaEntity vaga = vagaService.buscarVagaID(idVaga);
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(idCandidato);
		StatusVaga status = vagaService.buscarStatusID(idStatus);
		VagaCandidato vagaCandidato = vagaCandidatoRepository.save(new VagaCandidato(status, candidato, vaga));
		
		URI uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vagaCandidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoVagaDTO(vagaCandidato));
	}
	
	@CrossOrigin
	@PutMapping(path = "/candidatar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<CandidatoVagaDTO> atualizarStatus(@PathVariable Long id, @RequestParam(required = true) Long idStatus, UriComponentsBuilder uriBuilder) {
		VagaCandidato vagaCandidato = candidatoVagaService.buscarVagaCandidatoID(id);
		StatusVaga status = vagaService.buscarStatusID(idStatus);
		VagaCandidato vagaCandidatoAtualizado = candidatoVagaService.atualizarVagaCandidato(vagaCandidato, status);
		VagaCandidato vagaCandidatoSalvo = vagaCandidatoRepository.save(vagaCandidatoAtualizado);
		
		URI uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vagaCandidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoVagaDTO(vagaCandidatoSalvo));
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/status", produces = "application/json")
	@Transactional
	public Page<StatusDTO> listarStatusVaga(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<StatusVaga> status = statusRepository.findAll(paginacao);  
		return StatusDTO.converter(status);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/candidatos/{id}", produces = "application/json")
	@Transactional
	public Page<CandidatoAtualizadoDTO> listarCandidatosVaga(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Long idStatus = (long) 1;
		VagaEntity vaga = vagaService.buscarVagaID(id);
		StatusVaga status = vagaService.buscarStatusID(idStatus);
		List<VagaCandidato> vagaCandidato = vagaCandidatoRepository.findByVagaAndStatus(vaga, status);
		List<CandidatoEntity> candidatos = candidatoVagaService.listarCandidatos(vagaCandidato, paginacao);
		List<CandidatoAtualizadoDTO> dtos = candidatoResponseService.listarCandidatos(candidatos);
		return candidatoResponseService.paginarCandidatosDTO(dtos, paginacao);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/vagas/status", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarVagasStatus(@RequestParam(required = true) Long idCandidato, @RequestParam(required = true) Long idStatus, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		StatusVaga status = vagaService.buscarStatusID(idStatus);
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(idCandidato);
		List<VagaCandidato> vagaCandidato = candidatoVagaService.encontrarStatusVagasCandidato(status, candidato);
		List<VagaEntity> vagas = candidatoVagaService.listarVagas(vagaCandidato);
		List<VagaSalvaDTO> dtos = vagaResponseService.listarVagas(vagas);
		return vagaResponseService.paginarVagasDTO(dtos, paginacao);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/beneficio", produces = "application/json")
	@Transactional
	public Page<BeneficioDTO> listarBeneficios(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Beneficio> beneficios = beneficioRepository.findAll(paginacao);
		return BeneficioDTO.converter(beneficios);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/contrato", produces = "application/json")
	@Transactional
	public Page<ContratoDTO> listarTipoContrato(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<TipoContrato> tipos = tipoContratoRepository.findAll(paginacao);
		return ContratoDTO.converter(tipos);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/suporte", produces = "application/json")
	@Transactional
	public Page<SuporteDTO> listarSuportes(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<SuportePCD> suportes = suporteService.listarSuportesPCD(paginacao);
		return SuporteDTO.converter(suportes);
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/{id}", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarVagasCandidato(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		if(candidato != null) {
			List<VagaEntity> vagas = candidatoVagaService.buscarVagas(candidato);
			List<VagaSalvaDTO> dtos = vagaResponseService.listarVagas(vagas);
			return vagaResponseService.paginarVagasDTO(dtos, paginacao);
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/vagas/candidato/{id}", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarVagasCandidatoX(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
		if(candidato != null) {
			List<VagaCandidato> vagasCandidato = candidatoVagaService.buscarVagasCandidato(candidato);
			List<VagaEntity> vagas = candidatoVagaService.listarVagas(vagasCandidato);
			List<VagaSalvaDTO> dtos = vagaResponseService.listarVagas(vagas);
			return vagaResponseService.paginarVagasDTO(dtos, paginacao);
		} else {
			return null;
		}
	}
}
