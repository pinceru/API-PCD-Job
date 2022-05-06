package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.TipoDeficiencia;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.DeficienciaCandidato;
import com.pcdjob.repository.DeficienciaCandidatoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.TipoDeficienciaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class DeficienciaService {
	
	@Autowired
	private TipoDeficienciaRepository tipoDeficienciaRepository;
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@Autowired
	private DeficienciaCandidatoRepository deficienciaCandidatoRepository;
	
	public TipoDeficiencia buscarTipoID(Long id) {
		Optional<TipoDeficiencia> optional = tipoDeficienciaRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Deficiencia> listarDeficiencias() {
		return deficienciaRepository.findAll();
	}
	
	public void converterDeficiencias(CandidatoEntity candidato, List<Long> deficienciasRequisitadas) {
		int indice = 0;
		while(indice < deficienciasRequisitadas.size()) {
			Optional<Deficiencia> deficiencia = deficienciaRepository.findById(deficienciasRequisitadas.get(indice));
			Optional<DeficienciaCandidato> optional = deficienciaCandidatoRepository.findByDeficienciaAndCandidato(deficiencia.get(), candidato);
			if(!Verificar.verificarOptional(optional)) {
				deficienciaCandidatoRepository.save(new DeficienciaCandidato(deficiencia.get(), candidato));
			} else {
				List<Deficiencia> deficiencias = deficienciaRepository.findAll();
				deletarDeficiencias(deficiencias, deficienciasRequisitadas);
				
			}
			indice += 1;
		}
	}
	
	private boolean verificarDeficiencia(int indice, List<Long> deficienciasRequisitadas, List<Deficiencia> deficienciasExistentes) {
		if(deficienciasRequisitadas.contains(deficienciasExistentes.get(indice).getId())) {
			return true;
		} else {
			return false;
		}
	}
	
	private void deletarDeficiencias(List<Deficiencia> deficienciasExistentes, List<Long> deficienciasRequisitadas) {
		int indice = 0;
		while(indice < deficienciasExistentes.size()) {
			if(!verificarDeficiencia(indice, deficienciasRequisitadas, deficienciasExistentes)) {
				Optional<DeficienciaCandidato> optional = deficienciaCandidatoRepository.findByDeficiencia(deficienciasExistentes.get(indice));
				if(Verificar.verificarOptional(optional)) {
					deficienciaCandidatoRepository.delete(optional.get());
				}
			}
			indice++;
		}
	}
	
	public Page<Deficiencia> paginarDeficienciasTipo(TipoDeficiencia tipo, Pageable paginacao) {
		return deficienciaRepository.findByTipoDeficiencia(tipo, paginacao);
	}
}
