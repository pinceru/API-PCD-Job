package com.pcdjob.model.vaga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_beneficio_vaga")
public class VagaBeneficio {
	@Id
	@Column(name = "id_beneficio_vaga")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private VagaEntity vaga;
	@ManyToOne
	private Beneficio beneficio;
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
	public Beneficio getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}
	
}