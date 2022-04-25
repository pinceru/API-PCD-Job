package com.pcdjob.controller;

import java.net.URI;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.AtualizarVagaDTO;
import com.pcdjob.controller.dto.CandidatoAtualizadoDTO;
import com.pcdjob.controller.dto.InserirVagaDTO;
import com.pcdjob.controller.dto.StatusDTO;
import com.pcdjob.controller.dto.TipoContratoRepository;
import com.pcdjob.controller.dto.VagaDTO;
import com.pcdjob.controller.dto.VagaSalvaDTO;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.StatusVaga;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaCandidato;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.BeneficioRepository;
import com.pcdjob.repository.CandidatoRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.FormacaoDesejadaRepository;
import com.pcdjob.repository.HorarioRepository;
import com.pcdjob.repository.LocalTrabalhoRepository;
import com.pcdjob.repository.SalarioRepository;
import com.pcdjob.repository.StatusRepository;
import com.pcdjob.repository.SuporteRepository;
import com.pcdjob.repository.VagaBeneficioRepository;
import com.pcdjob.repository.VagaCandidatoRepository;
import com.pcdjob.repository.VagaDeficienciaRepository;
import com.pcdjob.repository.VagaRepository;
import com.pcdjob.repository.VagaSuporteRepository;
import com.pcdjob.service.CandidatoVagaService;

@RestController
@RequestMapping("/vaga")
public class VagaController {

	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private HorarioRepository horarioRepository;
	
	@Autowired
	private TipoContratoRepository tipoContratoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private SuporteRepository suporteRepository;
	
	@Autowired
	private VagaSuporteRepository vagaSuporteRepository;
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@Autowired 
	private VagaDeficienciaRepository vagaDeficienciaRepository;
	
	@Autowired
	private SalarioRepository salarioRepository;
	
	@Autowired
	private BeneficioRepository beneficioRepository;
	
	@Autowired
	private VagaBeneficioRepository vagaBeneficioRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private FormacaoDesejadaRepository formacaoRepository;
	
	@Autowired
	private LocalTrabalhoRepository localRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private VagaCandidatoRepository vagaCandidatoRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	CandidatoVagaService service = new CandidatoVagaService();
	
	@CrossOrigin
	@PostMapping(path = "/cadastrar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaDTO> cadastrarVaga(@PathVariable Long id, @RequestBody InserirVagaDTO insercaoDTO, UriComponentsBuilder uriBuilder) {
		Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
		TipoContrato contrato = insercaoDTO.converterTipoContrato(tipoContratoRepository);
		VagaEntity vaga = insercaoDTO.converter(empresa.get(), estadoRepository, cidadeRepository, contrato, horarioRepository, salarioRepository, localRepository);
		VagaEntity vagaSalva = vagaRepository.save(vaga);
		insercaoDTO.salvarSuportesVaga(vagaSalva, suporteRepository, vagaSuporteRepository);
		insercaoDTO.salvarDeficienciasVaga(vagaSalva, deficienciaRepository, vagaDeficienciaRepository);
		insercaoDTO.salvarBeneficiosVaga(vagaSalva, beneficioRepository, vagaBeneficioRepository);
		insercaoDTO.salvarFormacaoDesejada(vagaSalva, cursoRepository, formacaoRepository);
		
		URI uri = uriBuilder.path("/vaga/{id}")
				.buildAndExpand(vagaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(new VagaDTO(vagaSalva));
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarTodasVagas(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<VagaEntity> vagas = vagaRepository.findAll(paginacao);
		return VagaSalvaDTO.converter(vagas);
	}
	
	@CrossOrigin
	@GetMapping(path = "/buscar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaSalvaDTO> buscarVaga(@PathVariable Long id) {
		Optional<VagaEntity> vaga = vagaRepository.findById(id);
		if(vaga.isPresent()) {
			return ResponseEntity.ok(new VagaSalvaDTO(vaga.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@PutMapping(path = "/atualizar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaSalvaDTO> atualizarVaga(@PathVariable Long id, @RequestBody AtualizarVagaDTO atualizacaoDTO, UriComponentsBuilder uriBuilder) {
		VagaEntity vaga = atualizacaoDTO.converter(id, vagaRepository, salarioRepository, tipoContratoRepository, horarioRepository);
		atualizacaoDTO.atualizarDeficienciasVaga(vaga, deficienciaRepository, vagaDeficienciaRepository);
		atualizacaoDTO.atualizarSuportesVaga(vaga, suporteRepository, vagaSuporteRepository);
		atualizacaoDTO.atualizarBeneficiosVaga(vaga, beneficioRepository, vagaBeneficioRepository);
		atualizacaoDTO.atualizarFormacaoDesejada(vaga, cursoRepository, formacaoRepository);
		VagaEntity vagaAtualizada = vagaRepository.save(vaga);
		
		URI uri = uriBuilder.path("/vaga/{id}")
				.buildAndExpand(vagaAtualizada.getId()).toUri();
		return ResponseEntity.created(uri).body(new VagaSalvaDTO(vagaAtualizada));
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/deletar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> deletarVaga(@PathVariable Long id) {
		Optional<VagaEntity> vaga = vagaRepository.findById(id);
		if(vaga.isPresent()) {
			vagaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@GetMapping(path = "/listar/empresa/{id}", produces = "application/json")
	@Transactional
	public Page<VagaSalvaDTO> listarVagasEmresa(@PathVariable Long id, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
		Page<VagaEntity> vagas = vagaRepository.findAllByEmpresa(empresa.get(), paginacao);
		return VagaSalvaDTO.converter(vagas);
	}
	
	@CrossOrigin
	@PostMapping(path = "/candidatar", produces = "application/json")
	@Transactional
	public ResponseEntity<CandidatoVagaDTO> candidatarVaga(@RequestParam(required = true) Long idCandidato, @RequestParam(required = true) Long idStatus, @RequestParam(required = true) Long idVaga, 
			UriComponentsBuilder uriBuilder) {
		Optional<VagaEntity> vaga = vagaRepository.findById(idVaga);
		Optional<CandidatoEntity> candidato = candidatoRepository.findById(idCandidato);
		Optional<StatusVaga> status = statusRepository.findById(idStatus);
		VagaCandidato vagaCandidato = vagaCandidatoRepository.save(new VagaCandidato(status.get(), candidato.get(), vaga.get()));
		
		URI uri = uriBuilder.path("/vaga/{id}")
				.buildAndExpand(vagaCandidato.getId()).toUri();
		return ResponseEntity.created(uri).body(new CandidatoVagaDTO(vagaCandidato));
	}
	
	@CrossOrigin
	@PutMapping(path = "/candidatar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<CandidatoVagaDTO> atualizarStatus(@PathVariable Long id, @RequestParam(required = true) Long idStatus, UriComponentsBuilder uriBuilder) {
		VagaCandidato vagaCandidato = vagaCandidatoRepository.getOne(id);
		StatusVaga status = statusRepository.getOne(idStatus);
		VagaCandidato vagaCandidatoAtualizado = service.atualizarVagaCandidato(vagaCandidato, status);
		VagaCandidato vagaCandidatoSalvo = vagaCandidatoRepository.save(vagaCandidatoAtualizado);
		
		URI uri = uriBuilder.path("/vaga/{id}")
				.buildAndExpand(vagaCandidato.getId()).toUri();
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
		VagaEntity vaga = vagaRepository.getOne(id);
		StatusVaga status = statusRepository.getOne((long) 1);
		List<VagaCandidato> vagaCandidato = vagaCandidatoRepository.findByVagaAndStatus(vaga, status);
		Page<CandidatoEntity> candidatos = service.getCandidatos(vagaCandidato, paginacao);
		return CandidatoAtualizadoDTO.converter(candidatos);
	}
	
}
