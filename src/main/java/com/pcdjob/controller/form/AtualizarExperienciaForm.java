package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.candidato.ExperienciaProfissional;

public class AtualizarExperienciaForm {
	@NotNull @NotEmpty
	private String cargo;
	@Length(min = 8, max = 10)
	private String dataInicio;
	@NotNull @NotEmpty @Length(min = 8, max = 10)
	private String dataSaida;
	@Length(min = 8, max = 200)
	private String atribuicoes;
	@Length(min = 8, max = 200)
	private String nomeEmpresa;
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getAtribuicoes() {
		return atribuicoes;
	}

	public void setAtribuicoes(String atribuicoes) {
		this.atribuicoes = atribuicoes;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public ExperienciaProfissional converter(ExperienciaProfissional experiencia) {
		experiencia.setCargo(this.cargo);
		experiencia.setAtribuicoes(this.atribuicoes);
		experiencia.setDataInicio(this.dataInicio);
		experiencia.setDataSaida(this.dataSaida);
		experiencia.setNomeEmpresa(this.nomeEmpresa);
		return experiencia;
	}

}
