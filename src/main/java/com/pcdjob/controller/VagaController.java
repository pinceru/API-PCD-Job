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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcdjob.controller.dto.AtualizarVagaDTO;
import com.pcdjob.controller.dto.InserirVagaDTO;
import com.pcdjob.controller.dto.TipoContratoRepository;
import com.pcdjob.controller.dto.VagaDTO;
import com.pcdjob.controller.dto.VagaSalvaDTO;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.BeneficioRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.FormacaoDesejadaRepository;
import com.pcdjob.repository.HorarioRepository;
import com.pcdjob.repository.LocalTrabalhoRepository;
import com.pcdjob.repository.SalarioRepository;
import com.pcdjob.repository.SuporteRepository;
import com.pcdjob.repository.VagaBeneficioRepository;
import com.pcdjob.repository.VagaDeficienciaRepository;
import com.pcdjob.repository.VagaRepository;
import com.pcdjob.repository.VagaSuporteRepository;

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
	@GetMapping(path = "/atualizar/{id}", produces = "application/json")
	@Transactional
	public ResponseEntity<VagaSalvaDTO> atualizarVaga(@PathVariable Long id, @RequestBody AtualizarVagaDTO atualizacaoDTO, UriComponentsBuilder uriBuilder) {
		
	}
}
