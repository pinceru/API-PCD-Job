package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_email_candidato")
public class EmailCandidato {
	@Id
	@Column(name = "id_email_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	
	@ManyToOne
	private CandidatoEntity candidato;
	
	public EmailCandidato(String email, CandidatoEntity candidato) {
		this.email = email;
		this.candidato = candidato;
	}
	
	public EmailCandidato() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
}
