package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EnderecoCandidato;

public class EnderecoCandidatoForm {
	@NotNull @NotEmpty @Length(max = 50)
	private String rua;
	@NotNull @NotEmpty @Length(max = 50)
	private String bairro;
	@NotNull @NotEmpty @Length(min = 8, max = 9)
	private String cep;
	@NotNull @NotEmpty @Length(min = 2)
	private String sigla;
	@NotNull @NotEmpty @Length(max = 50)
	private String estado;
	@NotNull @NotEmpty @Length(max = 50)
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
