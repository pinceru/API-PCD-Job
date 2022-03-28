package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_experiencia_profissional")
public class ExperienciaProfissional {
	@Id
	@Column(name = "id_experiencia_profissional")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cargo;
	private String dataInicio;
	private String dataSaida;
	private String atribuicoes;
	@Column(name = "nome_empresa")
	private String nomeEmpresa;
	
	@ManyToOne
	private CandidatoEntity candidato;

	
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
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	
	public ExperienciaProfissional() {
		
	}
	
	public ExperienciaProfissional(String cargo, String dataInicio, String dataFim, String atribuicoes, String nomeEmpresa, CandidatoEntity candidato) {
		this.cargo = cargo;
		this.atribuicoes = atribuicoes;
		this.candidato = candidato;
		this.dataInicio = dataInicio;
		this.dataSaida = dataFim;
		this.nomeEmpresa = nomeEmpresa;
	}
	
}
