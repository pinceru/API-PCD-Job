package com.pcdjob.controller.dto.response;

public class ResponseExperienciaProfissional {
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
	
	public ResponseExperienciaProfissional() {
		
	}
	
	public ResponseExperienciaProfissional(Long id, String cargo, String dataInicio, String dataSaida, String atribuicoes, String nomeEmpresa) {
		this.id = id;
		this.atribuicoes = atribuicoes;
		this.cargo = cargo;
		this.dataSaida = dataSaida;
		this.dataInicio = dataInicio;
		this.nomeEmpresa = nomeEmpresa;
	}

}
