package com.pcdjob.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_area_atuacao")
public class AreaAtuacao {
	@Id
	@Column(name = "id_area_atuacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String areaAtuacao;
	@OneToMany(mappedBy = "areaAtuacao", cascade = CascadeType.ALL)
	private List<Curso> curso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public List<Curso> getCurso() {
		return curso;
	}
	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}
	
	
}
