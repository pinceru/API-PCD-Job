package com.pcdjob.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pcdjob.model.vaga.VagaSuportePCD;

@Entity
@Table(name = "tbl_suporte_pcd")
public class SuportePCD {
	@Id
	@Column(name = "id_suporte_pcd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String suporte;
	@OneToMany(mappedBy = "suporte")
	private List<VagaSuportePCD> vagaSuporte;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSuporte() {
		return suporte;
	}
	public void setSuporte(String suporte) {
		this.suporte = suporte;
	}
	public List<VagaSuportePCD> getVagaSuporte() {
		return vagaSuporte;
	}
	public void setVagaSuporte(List<VagaSuportePCD> vagaSuporte) {
		this.vagaSuporte = vagaSuporte;
	}
}
