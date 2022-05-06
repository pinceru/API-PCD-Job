package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.model.vaga.VagaSuportePCD;
import com.pcdjob.repository.SuporteRepository;
import com.pcdjob.repository.VagaSuporteRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class VagaSuporteService {
	
	@Autowired
	private SuporteRepository suporteRepository;
	
	@Autowired
	private VagaSuporteRepository vagaSuporteRepository;
	
	public void converterSuportes(VagaEntity vaga, List<Long> suportesForm) {
		if(suportesForm.size() > 0) {
			int indice = 0;
			while(indice < suportesForm.size()) {
				Optional<SuportePCD> suporte = suporteRepository.findById(suportesForm.get(indice));
				Optional<VagaSuportePCD> optional = vagaSuporteRepository.findBySuporteAndVaga(suporte.get(), vaga);
				if(!Verificar.verificarOptional(optional)) {
					vagaSuporteRepository.save(new VagaSuportePCD(vaga, suporte.get()));
				} else {
					List<SuportePCD> suportes = suporteRepository.findAll();
					deletarSuportes(suportes, suportesForm);
				}
				indice += 1;
			}
		}
	}
	
	private boolean verificarSuporte(int indice, List<Long> suportesForm, List<SuportePCD> suportesExistentes) {
		if(suportesForm.contains(suportesExistentes.get(indice).getId())) {
			return true;
		} else {
			return false;
		}
	}
	
	private void deletarSuportes(List<SuportePCD> suportesExistentes, List<Long> suportesForm) {
		int indice = 0;
		while(indice < suportesExistentes.size()) {
			if(!verificarSuporte(indice, suportesForm, suportesExistentes)) {
				Optional<VagaSuportePCD> optional = vagaSuporteRepository.findBySuporte(suportesExistentes.get(indice));
				if(Verificar.verificarOptional(optional)) {
					vagaSuporteRepository.delete(optional.get());
				}
			}
			indice++;
		}
	}
}
