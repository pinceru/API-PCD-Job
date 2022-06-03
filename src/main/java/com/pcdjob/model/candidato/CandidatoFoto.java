package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_foto_candidato")
public class CandidatoFoto {
	@Id
	@Column(name = "id_foto_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private byte[] foto;
	@OneToOne
	private CandidatoEntity candidato;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	
	public CandidatoFoto() {
		
	}
	
	public CandidatoFoto(byte[] foto, CandidatoEntity candidato) {
		this.foto = foto;
		this.candidato = candidato;
	}
}
