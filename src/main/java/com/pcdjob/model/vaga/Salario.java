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
	private String salario;
	private int visivel;
	@OneToMany(mappedBy = "salario")
	private List<VagaSalario> vagaSalario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public int getVisivel() {
		return visivel;
	}
	public void setVisivel(int visivel) {
		this.visivel = visivel;
	}
	public List<VagaSalario> getVagaSalario() {
		return vagaSalario;
	}
	public void setVagaSalario(List<VagaSalario> vagaSalario) {
		this.vagaSalario = vagaSalario;
	}
	
}
