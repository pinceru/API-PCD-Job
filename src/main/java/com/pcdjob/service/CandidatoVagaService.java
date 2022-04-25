package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.vaga.StatusVaga;
import com.pcdjob.model.vaga.VagaCandidato;

public class CandidatoVagaService {
	public VagaCandidato atualizarVagaCandidato(VagaCandidato vagaCandidato, StatusVaga statusVaga) {
		vagaCandidato.setStatus(statusVaga);
		return vagaCandidato;
	}
	
	public Page<CandidatoEntity> getCandidatos(List<VagaCandidato> candidatos, Pageable paginacao) {
		int indice = 0;
		List<CandidatoEntity> candidatoList = new ArrayList<>();
		while(indice < candidatos.size()) {
			CandidatoEntity novoCandidato = candidatos.get(indice).getCandidato();
			candidatoList.add(novoCandidato);
			indice++;
		}
		Page<CandidatoEntity> candidatoPage = new PageImpl<CandidatoEntity>(candidatoList, paginacao, candidatoList.size());
		return candidatoPage;
	}
}
