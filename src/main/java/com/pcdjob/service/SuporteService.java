package com.pcdjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.model.SuportePCD;
import com.pcdjob.repository.SuporteRepository;

@Service
public class SuporteService {
	
	@Autowired
	private SuporteRepository suporteRepository;
	
	public Page<SuportePCD> listarSuportesPCD(Pageable paginacao) {
		return suporteRepository.findAll(paginacao);
	}
	
//	public List<SuporteDTO> listarSuportesDTO(List<SuportePCD> suportes) {
//		int indice = 0;
//		List<SuporteDTO> dtos = new ArrayList<>();
//		while(indice < suportes.size()) {
//			SuporteDTO dto 
//		}
//	}
}
