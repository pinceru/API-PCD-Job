package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.Deficiencia;

@Entity
@Table(name = "tbl_deficiencia_candidato")
public class DeficienciaCandidato {
	@Id
	@Column(name = "id_deficiencia_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CandidatoEntity candidato;
	@ManyToOne
	private Deficiencia deficiencia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	public Deficiencia getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(Deficiencia deficiencia) {
		this.deficiencia = deficiencia;
	}
	
	public DeficienciaCandidato() {
		
	}
	public DeficienciaCandidato(Deficiencia deficiencia, CandidatoEntity candidato) {
		this.candidato = candidato;
		this.deficiencia = deficiencia;
	}
}
