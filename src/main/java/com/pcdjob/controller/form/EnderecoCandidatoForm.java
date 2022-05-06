package com.pcdjob.controller.form;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EnderecoCandidato;

public class EnderecoCandidatoForm {
	private String rua;
	private String bairro;
	private String cep;
	private String sigla;
	private String estado;
	private String cidade;
	private String numero;
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public EnderecoCandidato converter(CandidatoEntity candidato, Cidade cidade) {
		return new EnderecoCandidato(rua, numero, cep, bairro, cidade, candidato);
	}
	
}
