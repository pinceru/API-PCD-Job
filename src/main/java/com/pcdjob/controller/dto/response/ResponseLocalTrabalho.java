package com.pcdjob.controller.dto.response;

public class ResponseLocalTrabalho {
	private Long id;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private Long idCidade;
	private String estado;
	private String sigla;
	private Long idEstado;
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
	public Long getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
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
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public ResponseLocalTrabalho() {
		
	}
	
	public ResponseLocalTrabalho(Long id, String rua, String numero, String bairro, String cidade, Long idCidade, String estado, String sigla, Long idEstado, String cep) {
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.id = id;
		this.idCidade = idCidade;
		this.idEstado = idEstado;
		this.numero = numero;
		this.rua = rua;
		this.sigla = sigla;
	}
}
