package com.pcdjob.model.vaga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.SuportePCD;

@Entity
@Table(name = "tbl_vaga_suporte_pcd")
public class VagaSuportePCD {
	@Id
	@Column(name = "id_vaga_suporte_pcd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private SuportePCD suporte;
	@ManyToOne
	private VagaEntity vaga;
	public VagaSuportePCD(VagaEntity vaga, SuportePCD suportePCD) {
		this.suporte = suportePCD;
		this.vaga = vaga;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SuportePCD getSuporte() {
		return suporte;
	}
	public void setSuporte(SuportePCD suporte) {
		this.suporte = suporte;
	}
	public VagaEntity getVaga() {
		return vaga;
	}
	public void setVaga(VagaEntity vaga) {
		this.vaga = vaga;
	}
	
	public VagaSuportePCD() {
		
	}
	
}
