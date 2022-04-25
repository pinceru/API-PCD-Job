package com.pcdjob.model.vaga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.candidato.CandidatoEntity;

@Entity
@Table(name = "tbl_vaga_candidato")
public class VagaCandidato {
	@Id
	@Column(name = "id_vaga_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private StatusVaga status;
	@ManyToOne
	private CandidatoEntity candidato;
	@ManyToOne
	private VagaEntity vaga;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StatusVaga getStatus() {
		return status;
	}
	public void setStatus(StatusVaga status) {
		this.status = status;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	public VagaEntity getVaga() {
		return vaga;
	}
	public void setVaga(VagaEntity vaga) {
		this.vaga = vaga;
	}
	
	public VagaCandidato() {
		
	}
	
	public VagaCandidato(StatusVaga status, CandidatoEntity candidato, VagaEntity vaga) {
		this.status = status;
		this.candidato = candidato;
		this.vaga = vaga;
	}
}
