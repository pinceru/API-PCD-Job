package com.pcdjob.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pcdjob.model.candidato.CursoCandidato;
import com.pcdjob.model.vaga.FormacaoDesejada;

@Entity
@Table(name = "tbl_curso")
public class Curso {
	@Id
	@Column(name = "id_curso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String curso; 
	@ManyToOne
	private Nivel nivel;
	@ManyToOne
	private AreaAtuacao areaAtuacao;
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private List<CursoCandidato> cursoCandidato;
	@OneToMany(mappedBy = "curso")
	private List<FormacaoDesejada> formacaoDesejada;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public List<CursoCandidato> getCursoCandidato() {
		return cursoCandidato;
	}
	public void setCursoCandidato(List<CursoCandidato> cursoCandidato) {
		this.cursoCandidato = cursoCandidato;
	}
	public List<FormacaoDesejada> getFormacaoDesejada() {
		return formacaoDesejada;
	}
	public void setFormacaoDesejada(List<FormacaoDesejada> formacaoDesejada) {
		this.formacaoDesejada = formacaoDesejada;
	}
	
}
