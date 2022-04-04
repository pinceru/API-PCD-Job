package com.pcdjob.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_tipo_deficiencia")
public class TipoDeficiencia {
	@Id
	@Column(name = "id_tipo_deficiencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	@OneToMany(mappedBy = "tipoDeficiencia")
	private List<Deficiencia> deficiencia = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<Deficiencia> getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(List<Deficiencia> deficiencia) {
		this.deficiencia = deficiencia;
	}
	
	
}
