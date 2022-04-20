package com.pcdjob.model.vaga;

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
@Table(name = "tbl_beneficio")
public class Beneficio {
	@Id
	@Column(name = "id_beneficio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String beneficio;
	@OneToMany(mappedBy = "beneficio", cascade = CascadeType.ALL)
	private List<VagaBeneficio> vagaBeneficio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	public List<VagaBeneficio> getVagaBeneficio() {
		return vagaBeneficio;
	}
	public void setVagaBeneficio(List<VagaBeneficio> vagaBeneficio) {
		this.vagaBeneficio = vagaBeneficio;
	}
	
}
