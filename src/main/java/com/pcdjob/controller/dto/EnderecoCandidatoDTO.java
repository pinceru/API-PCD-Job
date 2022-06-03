package com.pcdjob.controller.dto;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EnderecoCandidato;

public class EnderecoCandidatoDTO {

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

	public EnderecoCandidatoDTO(EnderecoCandidato endereco) {
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade().getCidade();
		this.estado = endereco.getCidade().getEstado().getEstado();
		this.id = endereco.getId();
		this.numero = endereco.getNumero();
		this.rua = endereco.getRua();
		this.sigla = endereco.getCidade().getEstado().getSigla();
	}
	
	public EnderecoCandidatoDTO(CandidatoEntity candidato) {
		if(candidato.getEndereco() != null) {
			EnderecoCandidato endereco = candidato.getEndereco();
			this.bairro = endereco.getBairro();
			this.cep = endereco.getCep();
			this.cidade = endereco.getCidade().getCidade();
			this.estado = endereco.getCidade().getEstado().getEstado();
			this.id = endereco.getId();
			this.numero = endereco.getNumero();
			this.rua = endereco.getRua();
			this.sigla = endereco.getCidade().getEstado().getSigla();
		} 
	}

}
