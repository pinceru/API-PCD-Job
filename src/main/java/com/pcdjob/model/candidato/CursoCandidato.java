package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.Curso;

@Entity
@Table(name = "tbl_curso_candidato")
public class CursoCandidato {
	@Id
	@Column(name = "id_curso_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Curso curso;
	@ManyToOne
	private CandidatoEntity candidato;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	
	public CursoCandidato() {
		
	}
	
	public CursoCandidato(CandidatoEntity candidato, Curso curso) {
		this.candidato = candidato;
		this.curso = curso;
	}
}
