package com.pcdjob.model.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_foto_empresa")
public class EmpresaFoto {
	@Id
	@Column(name = "id_foto_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String foto;
	@OneToOne
	private EmpresaEntity empresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public EmpresaEntity getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}
	
	public EmpresaFoto() {
		
	}
	
	public EmpresaFoto(String foto, EmpresaEntity empresa) {
		this.foto = foto;
		this.empresa = empresa;
	}
}

