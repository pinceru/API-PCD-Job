package com.pcdjob.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.ExperienciaProfissional;

public class ExperienciaProfissionalForm {
	@NotNull @NotEmpty @Length(max = 50)
	private String cargo;
	@NotNull @NotEmpty @Length(min = 8, max = 10)
	private String dataInicio;
	@Length(min = 8, max = 10)
	private String dataSaida;
	@Length(max = 200)
	private String atribuicoes;
	@NotNull @NotEmpty @Length(min = 2, max = 50)
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

	public ExperienciaProfissional converter(CandidatoEntity candidato) {
		return new ExperienciaProfissional(cargo, dataInicio, dataSaida, atribuicoes, nomeEmpresa, candidato);
	}
}
