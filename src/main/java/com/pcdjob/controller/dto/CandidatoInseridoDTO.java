package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.candidato.CandidatoEntity;

public class CandidatoInseridoDTO {
	private Long id;
	private String nome;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public CandidatoInseridoDTO(CandidatoEntity candidato) {
		this.id = candidato.getId();
		this.nome = candidato.getNome();
	
	}

	public static Page<CandidatoInseridoDTO> converter(Page<CandidatoEntity> candidatos) {
		return candidatos.map(CandidatoInseridoDTO::new);
	}
}
