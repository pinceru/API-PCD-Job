package com.pcdjob.controller.dto;

import com.pcdjob.model.empresa.EnderecoEmpresa;

public class EnderecoEmpresaDTO {
	private Long id;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String sigla;
	private String cep;
	
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
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public EnderecoEmpresaDTO(EnderecoEmpresa endereco) {
		this.id = endereco.getId();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade().getCidade();
		this.bairro = endereco.getBairro();
		this.estado = endereco.getCidade().getEstado().getEstado();
		this.sigla = endereco.getCidade().getEstado().getSigla();
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
	}
	
	
}
