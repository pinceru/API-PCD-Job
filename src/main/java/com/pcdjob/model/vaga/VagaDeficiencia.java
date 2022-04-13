package com.pcdjob.model.vaga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.Deficiencia;

@Entity
@Table(name = "tbl_vaga_deficiencia")
public class VagaDeficiencia {
	@Id
	@Column(name = "id_vaga_deficiencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private VagaEntity vaga;
	@ManyToOne
	private Deficiencia deficiencia;
	
	public VagaDeficiencia(VagaEntity vaga, Deficiencia deficiencia) {
		this.vaga = vaga;
		this.deficiencia = deficiencia;
	}
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
	public Deficiencia getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(Deficiencia deficiencia) {
		this.deficiencia = deficiencia;
	}

	public VagaDeficiencia() {
		
	}
}
