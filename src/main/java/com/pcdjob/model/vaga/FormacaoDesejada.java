package com.pcdjob.model.vaga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.Curso;

@Entity
@Table(name = "tbl_formacao_desejada")
public class FormacaoDesejada {
	@Id
	@Column(name = "id_formacao_desejada")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Curso curso;
	@ManyToOne
	private VagaEntity vaga;
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
	public VagaEntity getVaga() {
		return vaga;
	}
	public void setVaga(VagaEntity vaga) {
		this.vaga = vaga;
	}
	
}
