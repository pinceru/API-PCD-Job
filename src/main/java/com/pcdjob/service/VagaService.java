package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.controller.form.AtualizarVagaForm;
import com.pcdjob.controller.form.VagaForm;
import com.pcdjob.model.vaga.Horario;
import com.pcdjob.model.vaga.Salario;
import com.pcdjob.model.vaga.StatusVaga;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.HorarioRepository;
import com.pcdjob.repository.SalarioRepository;
import com.pcdjob.repository.StatusRepository;
import com.pcdjob.repository.VagaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class VagaService {
	
	@Autowired
	private HorarioRepository horarioRepository;
	
	@Autowired
	private SalarioRepository salarioRepository;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private VagaSuporteService vagaSuporteService;
	
	@Autowired
	private VagaDeficienciaService vagaDeficienciaService;
	
	@Autowired
	private VagaBeneficioService vagaBeneficioService;
	
	@Autowired
	private FormacaoDesejadaService formacaoService;
	
	public Horario converterHorario(String horarioInicio, String horarioSaida, int status) {
		Optional<Horario> optional = horarioRepository.findByHorarioInicioAndHorarioSaidaAndVisivel(horarioInicio, horarioSaida, status);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return horarioRepository.save(new Horario(horarioInicio, horarioSaida, status));
		}
	}
	
	public Salario converterSalario(String salario, int status) {
		Optional<Salario> optional = salarioRepository.findBySalarioAndVisivel(salario, status);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return salarioRepository.save(new Salario(salario, status));
		}
	}
	
	public VagaEntity buscarVagaID(Long id) {
		Optional<VagaEntity> optional = vagaRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public StatusVaga buscarStatusID(Long id) {
		Optional<StatusVaga> optional = statusRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void deletarVaga(VagaEntity vaga) {
		vagaRepository.delete(vaga);
	}
	
	public void adicionarRelacionamentosVaga(VagaEntity vagaSalva, VagaForm form) {
		vagaSuporteService.converterSuportes(vagaSalva, form.getSuporte());
		vagaDeficienciaService.converterDeficiencias(vagaSalva, form.getDeficiencia());
		vagaBeneficioService.converterBeneficio(vagaSalva, form.getBeneficio());
		formacaoService.converterCurso(vagaSalva, form.getFormacaoDesejada());
	}
	
	public void atualizarRelacionamentosVaga(VagaEntity vagaAtualizada, AtualizarVagaForm form) {
		vagaSuporteService.converterSuportes(vagaAtualizada, form.getSuporte());
		vagaDeficienciaService.converterDeficiencias(vagaAtualizada, form.getDeficiencia());
		vagaBeneficioService.converterBeneficio(vagaAtualizada, form.getBeneficio());
		formacaoService.converterCurso(vagaAtualizada, form.getFormacaoDesejada());
	}
}
