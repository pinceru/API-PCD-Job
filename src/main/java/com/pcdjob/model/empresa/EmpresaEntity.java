package com.pcdjob.model.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.vaga.VagaEntity;

@Entity
@Table(name = "tbl_empresa")
public class EmpresaEntity {
	@Id
	@Column(name = "id_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String senha;
	private String descricao;
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<EmailEmpresa> emailEmpresa = new ArrayList<>();
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<TelefoneEmpresa> telefoneEmpresa = new ArrayList<>();
	@ManyToOne
	private AreaAtuacao areaAtuacao;
	@OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL)
	private EnderecoEmpresa endereco;
	@OneToMany(mappedBy = "empresa")
	private List<VagaEntity> vaga;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public List<TelefoneEmpresa> getTelefoneEmpresa() {
		return telefoneEmpresa;
	}
	public void setTelefoneEmpresa(List<TelefoneEmpresa> telefoneEmpresa) {
		this.telefoneEmpresa = telefoneEmpresa;
	}
	public EnderecoEmpresa getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoEmpresa endereco) {
		this.endereco = endereco;
	}
	public List<VagaEntity> getVaga() {
		return vaga;
	}
	public void setVaga(List<VagaEntity> vaga) {
		this.vaga = vaga;
	}
	public EmpresaEntity() {
		
	}
	
	public EmpresaEntity(String nome, String senha, AreaAtuacao areaAtuacao) {
		this.senha = senha;
		this.nome = nome;
		this.areaAtuacao = areaAtuacao;
	}
	
	public EmpresaEntity(String nome, String senha, String descricao, AreaAtuacao areaAtuacao) {
		this.senha = senha;
		this.nome = nome;
		this.descricao = descricao;
		this.areaAtuacao = areaAtuacao;
	}
	
}
