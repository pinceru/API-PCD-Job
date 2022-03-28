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
@Table(name = "tbl_nivel")
public class Nivel {
	@Id
	@Column(name = "id_nivel")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nivel;
	@OneToMany(mappedBy = "nivel", cascade = CascadeType.ALL)
	private List<Curso> curso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public List<Curso> getCurso() {
		return curso;
	}
	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}
	
}
