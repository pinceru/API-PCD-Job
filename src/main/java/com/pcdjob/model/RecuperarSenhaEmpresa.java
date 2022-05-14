package com.pcdjob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.empresa.EmailEmpresa;

@Entity
@Table(name = "tbl_recuperar_empresa")
public class RecuperarSenhaEmpresa {
	@Id
	@Column(name = "id_recuperar_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer codigo;
	@ManyToOne
	private EmailEmpresa email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public EmailEmpresa getEmail() {
		return email;
	}
	public void setEmail(EmailEmpresa email) {
		this.email = email;
	}
	
	public RecuperarSenhaEmpresa(Integer codigo, EmailEmpresa email) {
		this.email = email;
		this.codigo = codigo;
	}
}
