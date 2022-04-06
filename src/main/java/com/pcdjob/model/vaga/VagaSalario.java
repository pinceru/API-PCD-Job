package com.pcdjob.model.vaga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_vaga_salario")
public class VagaSalario {
	@Id
	@Column(name = "id_vaga_salario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private VagaEntity vaga;
	@ManyToOne
	private Salario salario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VagaEntity getVaga() {
		return vaga;
	}
	public void setVaga(VagaEntity vaga) {
		this.vaga = vaga;
	}
	public Salario getSalario() {
		return salario;
	}
	public void setSalario(Salario salario) {
		this.salario = salario;
	}
	
}
