package com.pcdjob.controller.dto.response;

public class ResponseEnderecoEmpresa {
	private Long id;
	private String rua;
	private String numero;
	private String cep;
	private String bairro;
	private Long idCidade;
	private String cidade;
	private Long idEstado;
	private String estado;
	private String sigla;
	
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Long getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
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
	public ResponseEnderecoEmpresa(Long id, String rua, String numero, String cep, String bairro, Long idCidade, String cidade, Long idEstado, String estado, String sigla) {
		this.id = id;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.idCidade = idCidade;
		this.idEstado = idEstado;
		this.numero = numero;
		this.sigla = sigla;
		this.rua = rua;
	}
}
