package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_telefone_candidato")
public class TelefoneCandidato {
	@Id
	@Column(name = "id_telefone_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	@ManyToOne
	private CandidatoEntity candidato;
	
	public TelefoneCandidato() {
		
	}
	
	public TelefoneCandidato(CandidatoEntity candidato, String numero) {
		this.candidato = candidato;
		this.numero = numero;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	
}
