package com.pcdjob.controller.form;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.candidato.EnderecoCandidato;

public class AtualizarEnderecoCandidatoForm {
	private String rua;
	private String cep;
	private String cidade;
	private String bairro;
	private String numero;
	private String estado;
	private String sigla;

	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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

	public EnderecoCandidato converter(EnderecoCandidato endereco, Cidade cidade) {
		endereco.setCidade(cidade);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setNumero(numero);
		endereco.setRua(rua);
		return endereco;
	}
}
