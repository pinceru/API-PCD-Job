package com.pcdjob.model.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcdjob.model.Cidade;

@Entity
@Table(name = "tbl_endereco_empresa")
public class EnderecoEmpresa {
	@Id
	@Column(name = "id_endereco_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String numero;
	private String cep;
	private String bairro;
	@ManyToOne
	private Cidade cidade;
	@OneToOne
	private EmpresaEntity empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public EmpresaEntity getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}
	
	public EnderecoEmpresa() {
		
	}
	
	public EnderecoEmpresa(String rua, String numero, String bairro, String cep, Cidade cidade, EmpresaEntity empresa) {
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.empresa = empresa;
		this.numero = numero;
		this.rua = rua;
	}
}
