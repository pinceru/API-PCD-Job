package com.pcdjob.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.Genero;

public class AtualizarCandidatoForm {
	@NotNull @NotEmpty @Length(min = 2, max = 50)
	private String nome;
	@Length(max = 50)
	private String nomeSocial;
	@NotNull @NotEmpty @Length(min = 8, max = 10)
	private String dataNascimento;
	private String informacoes;
	@NotNull @NotEmpty
	private String genero;
	@NotEmpty
	private List<Long> deficiencia;
	private List<String> telefone;
	@NotEmpty
	private List<String> email;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeSocial() {
		return nomeSocial;
	}
	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public String getGenero() {
		return genero;
	}
	public void setId_genero(String genero) {
		this.genero = genero;
	}
	public List<Long> getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(List<Long> deficiencia) {
		this.deficiencia = deficiencia;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}

	public CandidatoEntity converter(CandidatoEntity candidato, Genero generoObj) {
		candidato.setNome(this.nome);
		candidato.setDataNascimento(this.dataNascimento);
		candidato.setNomeSocial(this.nomeSocial);
		candidato.setInformacoes(this.informacoes);
		candidato.setGenero(generoObj);
		return candidato;
	}
}

