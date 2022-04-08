package com.pcdjob.controller.dto;

import java.util.List;

import com.pcdjob.model.vaga.VagaEntity;

public class InserirVagaDTO {
	private int status;
	private String titulo;
	private String descricao;
	private String requisitos;
	private String tipoContrato;
	private String salario;
	private List<Long> suporte;
	private List<Long> beneficio;
	private List<Long> formacaoDesejada;
	private String rua;
	private String cidade;
	private String estado;
	private String sigla;
	private String bairro;
	private String numero;
	private String cep;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public List<Long> getSuporte() {
		return suporte;
	}
	public void setSuporte(List<Long> suporte) {
		this.suporte = suporte;
	}
	public List<Long> getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(List<Long> beneficio) {
		this.beneficio = beneficio;
	}
	public List<Long> getFormacaoDesejada() {
		return formacaoDesejada;
	}
	public void setFormacaoDesejada(List<Long> formacaoDesejada) {
		this.formacaoDesejada = formacaoDesejada;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public VagaEntity converter() {
		
	}
	
}
