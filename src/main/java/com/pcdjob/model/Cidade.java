package com.pcdjob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cidade")
public class Cidade {
	@Id
	@Column(name = "id_cidade")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	@ManyToOne
	private Estado estado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Cidade() {
		
	}
	
	public Cidade(String cidade, Estado estado) {
		this.cidade = cidade;
		this.estado = estado;
	}
	
}
