package com.pcdjob.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pcdjob.model.candidato.DeficienciaCandidato;

@Entity
@Table(name = "tbl_deficiencia")
public class Deficiencia {
	@Id
	@Column(name = "id_deficiencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String deficiencia;
	@ManyToOne
	private TipoDeficiencia tipoDeficiencia;
	
	@OneToMany(mappedBy = "deficiencia", cascade = CascadeType.ALL)
	private List<DeficienciaCandidato> deficienciaCandidato;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}
	public TipoDeficiencia getTipoDeficiencia() {
		return tipoDeficiencia;
	}
	public void setTipoDeficiencia(TipoDeficiencia tipoDeficiencia) {
		this.tipoDeficiencia = tipoDeficiencia;
	}
	public List<DeficienciaCandidato> getDeficienciaCandidato() {
		return deficienciaCandidato;
	}
	public void setDeficienciaCandidato(List<DeficienciaCandidato> deficienciaCandidato) {
		this.deficienciaCandidato = deficienciaCandidato;
	}
	
	
	
}
