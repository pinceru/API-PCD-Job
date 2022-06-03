package com.pcdjob.controller.dto;

import java.util.List;

import com.pcdjob.controller.dto.response.ResponseCursoCandidato;
import com.pcdjob.controller.dto.response.ResponseDeficiencia;
import com.pcdjob.controller.dto.response.ResponseEmailCandidato;
import com.pcdjob.controller.dto.response.ResponseExperienciaProfissional;
import com.pcdjob.controller.dto.response.ResponseTelefoneCandidato;
import com.pcdjob.model.candidato.CandidatoEntity;

public class CandidatoAtualizadoDTO {
	private Long id;
	private String nome;
	private String nomeSocial;
	private String genero;
	private String dataNascimento;
	private String informacoes;
	private String curriculo;
	private List<ResponseEmailCandidato> email;
	private List<ResponseTelefoneCandidato> telefone;
	private List<ResponseDeficiencia> deficiencia;
	private List<ResponseExperienciaProfissional> experiencia;
	private List<ResponseCursoCandidato> curso;
	private EnderecoCandidatoDTO endereco;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getNomeSocial() {
		return nomeSocial;
	}
	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
	public List<ResponseEmailCandidato> getEmail() {
		return email;
	}
	public void setEmail(List<ResponseEmailCandidato> email) {
		this.email = email;
	}
	public List<ResponseTelefoneCandidato> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<ResponseTelefoneCandidato> telefone) {
		this.telefone = telefone;
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
	public List<ResponseDeficiencia> getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(List<ResponseDeficiencia> deficiencia) {
		this.deficiencia = deficiencia;
	}
	public List<ResponseExperienciaProfissional> getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(List<ResponseExperienciaProfissional> experiencia) {
		this.experiencia = experiencia;
	}
	public List<ResponseCursoCandidato> getCurso() {
		return curso;
	}
	public void setCurso(List<ResponseCursoCandidato> curso) {
		this.curso = curso;
	}
	public EnderecoCandidatoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoCandidatoDTO endereco) {
		this.endereco = endereco;
	}
	public String getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
	}
	
	public CandidatoAtualizadoDTO(CandidatoEntity candidato, List<ResponseEmailCandidato> emails, List<ResponseTelefoneCandidato> telefones,
			List<ResponseDeficiencia> deficiencias, List<ResponseExperienciaProfissional> experiencia, List<ResponseCursoCandidato> cursos, EnderecoCandidatoDTO endereco) {
		this.id = candidato.getId();
		this.nome = candidato.getNome();
		this.nomeSocial = candidato.getNomeSocial();
		this.curriculo = candidato.getCurriculo();
		this.genero = candidato.getGenero().getGenero();
		this.dataNascimento = candidato.getDataNascimento();
		this.informacoes = candidato.getInformacoes();
		this.email = emails;
		this.telefone = telefones;
		this.deficiencia = deficiencias;
		this.experiencia = experiencia;
		this.curso = cursos;
		this.endereco = endereco;
	} 
}