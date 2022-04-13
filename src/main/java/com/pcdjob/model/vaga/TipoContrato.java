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
@Table(name = "tbl_tipo_contrato")
public class TipoContrato {
	@Id
	@Column(name = "id_tipo_contrato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipoContrato;
	@OneToMany(mappedBy = "tipoContrato")
	private List<VagaEntity> vaga;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public List<VagaEntity> getVaga() {
		return vaga;
	}
	public void setVaga(List<VagaEntity> vaga) {
		this.vaga = vaga;
	}
	
	public TipoContrato() {
		
	}
}
