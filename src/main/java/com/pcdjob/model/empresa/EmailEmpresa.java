package com.pcdjob.model.empresa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pcdjob.model.RecuperarSenhaEmpresa;

@Entity
@Table(name = "tbl_email_empresa")
public class EmailEmpresa {
	@Id
	@Column(name = "id_email_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	@OneToMany(mappedBy = "email")
	private List<RecuperarSenhaEmpresa> recuperar;
	
	@ManyToOne
	private EmpresaEntity empresa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public EmpresaEntity getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}

	public EmailEmpresa() {
		
	}
	
	public EmailEmpresa(String email, EmpresaEntity empresa) {
		this.email = email;
		this.empresa = empresa;
	}
}
