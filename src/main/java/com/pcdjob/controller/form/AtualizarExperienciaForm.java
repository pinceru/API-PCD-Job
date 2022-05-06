package com.pcdjob.controller.dto;

import java.util.Optional;

import com.pcdjob.model.candidato.ExperienciaProfissional;
import com.pcdjob.repository.ExperienciaProfissionalRepository;

public class AtualizarExperienciaDTO {
	private String cargo;
	private String dataInicio;
	private String dataSaida;
	private String atribuicoes;
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

	public ExperienciaProfissional converter(Long id, ExperienciaProfissionalRepository experienciaProfissionalRepository) {
		Optional<ExperienciaProfissional> optional = experienciaProfissionalRepository.findById(id);
		ExperienciaProfissional experiencia = optional.get();
		experiencia.setCargo(this.cargo);
		experiencia.setAtribuicoes(this.atribuicoes);
		experiencia.setDataInicio(this.dataInicio);
		experiencia.setDataSaida(this.dataSaida);
		experiencia.setNomeEmpresa(this.nomeEmpresa);
		return experiencia;
	}

}
