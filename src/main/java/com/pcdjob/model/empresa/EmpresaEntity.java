package com.pcdjob.model.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_empresa")
public class EmpresaEntity {
	@Id
	@Column(name = "id_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String senha;
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<EmailEmpresa> emailEmpresa = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<EmailEmpresa> getEmailEmpresa() {
		return emailEmpresa;
	}
	public void setEmailEmpresa(List<EmailEmpresa> emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}
	
	public EmpresaEntity() {
		
	}
	
	public EmpresaEntity(String nome, String senha) {
		this.senha = senha;
		this.nome = nome;
	}
	
}
