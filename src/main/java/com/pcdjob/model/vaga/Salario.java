package com.pcdjob.model.vaga;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_salario")
public class Salario {
	@Id
	@Column(name = "id_salario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Float salario;
	private int visivel;
	@OneToMany(mappedBy = "salario")
	private List<VagaEntity> vaga;
	
	public Salario(Float salario, int visivel) {
		this.salario = salario;
		this.visivel = visivel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	public int getVisivel() {
		return visivel;
	}
	public void setVisivel(int visivel) {
		this.visivel = visivel;
	}
	public List<VagaEntity> getVaga() {
		return vaga;
	}
	public void setVaga(List<VagaEntity> vaga) {
		this.vaga = vaga;
	}
	
	public Salario() {
		
	}
	
}
