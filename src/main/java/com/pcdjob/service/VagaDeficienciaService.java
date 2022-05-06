package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.vaga.VagaDeficiencia;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.VagaDeficienciaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class VagaDeficienciaService {
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@Autowired
	private VagaDeficienciaRepository vagaDeficienciaRepository;
	
	public void converterDeficiencias(VagaEntity vaga, List<Long> deficienciasRequisitadas) {
		int indice = 0;
		while(indice < deficienciasRequisitadas.size()) {
			Optional<Deficiencia> deficiencia = deficienciaRepository.findById(deficienciasRequisitadas.get(indice));
			Optional<VagaDeficiencia> optional = vagaDeficienciaRepository.findByDeficienciaAndVaga(deficiencia.get(), vaga);
			if(!Verificar.verificarOptional(optional)) {
				vagaDeficienciaRepository.save(new VagaDeficiencia(vaga, deficiencia.get()));
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
				Optional<VagaDeficiencia> optional = vagaDeficienciaRepository.findByDeficiencia(deficienciasExistentes.get(indice));
				if(Verificar.verificarOptional(optional)) {
					vagaDeficienciaRepository.delete(optional.get());
				}
			}
			indice++;
		}
	}
}
