package com.pcdjob.controller.dto;

import java.util.List;

import com.pcdjob.controller.dto.response.ResponseBeneficio;
import com.pcdjob.controller.dto.response.ResponseDeficiencia;
import com.pcdjob.controller.dto.response.ResponseEmpresa;
import com.pcdjob.controller.dto.response.ResponseFormacao;
import com.pcdjob.controller.dto.response.ResponseHorario;
import com.pcdjob.controller.dto.response.ResponseLocalTrabalho;
import com.pcdjob.controller.dto.response.ResponseSalario;
import com.pcdjob.controller.dto.response.ResponseSuporte;
import com.pcdjob.controller.dto.response.ResponseTipoContrato;
import com.pcdjob.model.vaga.VagaEntity;

public class VagaSalvaDTO {

	private Long id;
	private int status;
	private String titulo;
	private String descricao;
	private String requisitos;
	private ResponseTipoContrato tipoContrato;
	private ResponseSalario salario;
	private List<ResponseSuporte> suporte;
	private List<ResponseBeneficio> beneficio;
	private List<ResponseFormacao> formacaoDesejada;
	private List<ResponseDeficiencia> deficiencia;
	private ResponseLocalTrabalho localTrabalho;
	private ResponseHorario horario;
	private ResponseEmpresa empresa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ResponseTipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(ResponseTipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public ResponseSalario getSalario() {
		return salario;
	}

	public void setSalario(ResponseSalario salario) {
		this.salario = salario;
	}

	public List<ResponseSuporte> getSuporte() {
		return suporte;
	}

	public void setSuporte(List<ResponseSuporte> suporte) {
		this.suporte = suporte;
	}

	public List<ResponseBeneficio> getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(List<ResponseBeneficio> beneficio) {
		this.beneficio = beneficio;
	}

	public List<ResponseFormacao> getFormacaoDesejada() {
		return formacaoDesejada;
	}

	public void setFormacaoDesejada(List<ResponseFormacao> formacaoDesejada) {
		this.formacaoDesejada = formacaoDesejada;
	}

	public List<ResponseDeficiencia> getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(List<ResponseDeficiencia> deficiencia) {
		this.deficiencia = deficiencia;
	}

	public ResponseLocalTrabalho getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(ResponseLocalTrabalho localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public ResponseHorario getHorario() {
		return horario;
	}

	public void setHorario(ResponseHorario horario) {
		this.horario = horario;
	}
	public ResponseEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ResponseEmpresa empresa) {
		this.empresa = empresa;
	}

	public VagaSalvaDTO(VagaEntity vaga, ResponseLocalTrabalho local, ResponseHorario horario, ResponseTipoContrato tipoContrato, ResponseSalario salario, 
			List<ResponseSuporte> suportes, List<ResponseBeneficio> beneficios, List<ResponseFormacao> formacao, List<ResponseDeficiencia> deficiencias, ResponseEmpresa empresa) {
		
		this.id = vaga.getId();
		this.status = vaga.getStatus();
		this.titulo = vaga.getTitulo();
		this.descricao = vaga.getDescricao();
		this.requisitos = vaga.getRequisitos();
		this.localTrabalho = local;
		this.horario = horario;
		this.tipoContrato = tipoContrato;
		this.salario = salario;
		this.suporte = suportes;
		this.beneficio = beneficios;
		this.formacaoDesejada = formacao;
		this.deficiencia = deficiencias;
		this.empresa = empresa;
	}

}
