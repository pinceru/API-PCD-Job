package com.pcdjob.controller.dto;

import com.pcdjob.model.vaga.VagaCandidato;

public class CandidatoVagaDTO {
	private Long id;
	private Long idCandidato;
	private String candidato;
	private Long idVaga;
	private String vaga;
	private Long idStatus;
	private String status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public String getCandidato() {
		return candidato;
	}
	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}
	public Long getIdVaga() {
		return idVaga;
	}
	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	public String getVaga() {
		return vaga;
	}
	public void setVaga(String vaga) {
		this.vaga = vaga;
	}
	public Long getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public CandidatoVagaDTO(VagaCandidato vagaCandidato) {
		this.id = vagaCandidato.getId();
		this.idCandidato = vagaCandidato.getCandidato().getId();
		this.candidato = vagaCandidato.getCandidato().getNome();
		this.idVaga = vagaCandidato.getVaga().getId();
		this.vaga = vagaCandidato.getVaga().getTitulo();
		this.idStatus = vagaCandidato.getStatus().getId();
		this.status = vagaCandidato.getStatus().getStatus();
	}

}
