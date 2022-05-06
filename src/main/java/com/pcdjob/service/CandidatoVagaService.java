package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.vaga.StatusVaga;
import com.pcdjob.model.vaga.VagaCandidato;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.VagaCandidatoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class CandidatoVagaService {
	
	@Autowired
	private VagaCandidatoRepository vagaCandidatoRepository;
	
	public List<VagaCandidato> encontrarStatusVagasCandidato(StatusVaga status, CandidatoEntity candidato) {
		return vagaCandidatoRepository.findByCandidatoAndStatus(candidato, status);
	}
	
	public VagaCandidato buscarVagaCandidatoID(Long id) {
		Optional<VagaCandidato> optional = vagaCandidatoRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public VagaCandidato atualizarVagaCandidato(VagaCandidato vagaCandidato, StatusVaga statusVaga) {
		vagaCandidato.setStatus(statusVaga);
		return vagaCandidato;
	}
	
	public List<CandidatoEntity> listarCandidatos(List<VagaCandidato> candidatos, Pageable paginacao) {
		int indice = 0;
		List<CandidatoEntity> candidatoList = new ArrayList<>();
		while(indice < candidatos.size()) {
			CandidatoEntity novoCandidato = candidatos.get(indice).getCandidato();
			candidatoList.add(novoCandidato);
			indice++;
		}
		return candidatoList;
	}
	
	public List<VagaEntity> listarVagas(List<VagaCandidato> vagas) {
		int indice = 0;
		List<VagaEntity> vagaList = new ArrayList<>();
		while(indice < vagas.size()) {
			VagaEntity novaVaga = vagas.get(indice).getVaga();
			vagaList.add(novaVaga);
			indice++;
		}
		return vagaList;
	}
	
	
}
