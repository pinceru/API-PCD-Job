package com.pcdjob.model.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_email_empresa")
public class EmailEmpresa {
	@Id
	@Column(name = "id_email_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	
	@ManyToOne
	private EmpresaEntity empresa;
	
	public EmailEmpresa(String email, EmpresaEntity empresa) {
		this.email = email;
		this.empresa = empresa;
	}
}
