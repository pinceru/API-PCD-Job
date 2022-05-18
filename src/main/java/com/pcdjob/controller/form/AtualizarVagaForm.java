package com.pcdjob.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.vaga.Horario;
import com.pcdjob.model.vaga.Salario;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaEntity;

public class AtualizarVagaForm {
	@NotNull @NotEmpty @Length(max = 1)
	private int status;
	@NotNull @NotEmpty @Length(max = 50)
	private String titulo;
	@Length(max = 200)
	private String descricao;
	@Length(max = 200)
	private String requisitos;
	@NotNull @NotEmpty
	private Long tipoContrato;
	@NotNull @NotEmpty @Length(min = 4, max = 15)
	private String salario;
	@NotNull @NotEmpty @Length(max = 1)
	private int statusSalario;
	private List<Long> suporte;
	private List<Long> beneficio;
	private List<Long> formacaoDesejada;
	private List<Long> deficiencia;
	private String rua;
	private String cidade;
	private String estado;
	private String sigla;
	private String bairro;
	private String numero;
	private String cep;
	@NotNull @NotEmpty @Length(max = 5)
	private String horarioInicio;
	@NotNull @NotEmpty @Length(max = 5)
	private String horarioSaida;
	@NotNull @NotEmpty @Length(max = 1)
	private int statusHorario;
	
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
	public Long getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(Long tipoContrato) {
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
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioSaida() {
		return horarioSaida;
	}
	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}
	public int getStatusHorario() {
		return statusHorario;
	}
	public void setStatusHorario(int statusHorario) {
		this.statusHorario = statusHorario;
	}
	public List<Long> getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(List<Long> deficiencia) {
		this.deficiencia = deficiencia;
	}
	public int getStatusSalario() {
		return statusSalario;
	}
	public void setStatusSalario(int statusSalario) {
		this.statusSalario = statusSalario;
	}
	
	public VagaEntity converter(VagaEntity vaga, Salario salario, TipoContrato tipoContrato, Horario horario) {
		vaga.setDescricao(descricao);
		vaga.setStatus(status);
		vaga.setTitulo(titulo);
		vaga.setSalario(salario);
		vaga.setTipoContrato(tipoContrato);
		vaga.setHorario(horario);
		return vaga;
	}
}
