package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.candidato.ExperienciaProfissional;

public class ExperienciaProfissionalDTO {
	private Long id;
	private String cargo;
	private String dataInicio;
	private String dataSaida;
	private String atribuicoes;
	private String nomeEmpresa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ExperienciaProfissionalDTO(ExperienciaProfissional experienciaProfissional) {
		this.id = experienciaProfissional.getId();
		this.atribuicoes = experienciaProfissional.getAtribuicoes();
		this.cargo = experienciaProfissional.getCargo();
		this.dataInicio = experienciaProfissional.getDataInicio();
		this.dataSaida = experienciaProfissional.getDataSaida();
		this.nomeEmpresa = experienciaProfissional.getNomeEmpresa();
		
	}

	public static Page<ExperienciaProfissionalDTO> converter(Page<ExperienciaProfissional> experiencias) {
		return experiencias.map(ExperienciaProfissionalDTO::new);
	}

}
