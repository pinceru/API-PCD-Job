package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.vaga.Beneficio;
import com.pcdjob.model.vaga.VagaBeneficio;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.BeneficioRepository;
import com.pcdjob.repository.VagaBeneficioRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class VagaBeneficioService {
	
	@Autowired
	private BeneficioRepository beneficioRepository;
	
	@Autowired
	private VagaBeneficioRepository vagaBeneficioRepository;
	
	public void converterBeneficio(VagaEntity vaga, List<Long> beneficiosForm) {		
		int indice = 0;
		while(indice < beneficiosForm.size()) {
			Optional<Beneficio> beneficio = beneficioRepository.findById(beneficiosForm.get(indice));
			Optional<VagaBeneficio> optional = vagaBeneficioRepository.findByBeneficioAndVaga(beneficio.get(), vaga);
			if(!Verificar.verificarOptional(optional)) {
				vagaBeneficioRepository.save(new VagaBeneficio(vaga, beneficio.get()));
			} else {
				List<Beneficio> beneficios = beneficioRepository.findAll();
				deletarBeneficios(beneficios, beneficiosForm);
			}
			indice += 1;
		}
	}
	
	private boolean verificarBeneficio(int indice, List<Long> beneficiosForm, List<Beneficio> beneficiosExistentes) {
		if(beneficiosForm.contains(beneficiosExistentes.get(indice).getId())) {
			return true;
		} else {
			return false;
		}
	}
	
	private void deletarBeneficios(List<Beneficio> beneficiosExistentes, List<Long> beneficiosForm) {
		int indice = 0;
		while(indice < beneficiosExistentes.size()) {
			if(!verificarBeneficio(indice, beneficiosForm, beneficiosExistentes)) {
				Optional<VagaBeneficio> optional = vagaBeneficioRepository.findByBeneficio(beneficiosExistentes.get(indice));
				if(Verificar.verificarOptional(optional)) {
					vagaBeneficioRepository.delete(optional.get());
				}
			}
			indice++;
		}
	}
}
