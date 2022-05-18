package com.pcdjob.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.TelefoneEmpresa;

public class AtualizarEmpresaForm {
	@NotNull @NotEmpty @Length(min = 2, max = 50)
	private String nome;
	private String descricao;
	@NotEmpty
	private List<String> email;
	private List<String> telefone;
	@NotNull @NotEmpty
	private Long areaAtuacao;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String sigla;
	private String cep;
	private String numero;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}
	public Long getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(Long areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public EmpresaEntity converter(EmpresaEntity empresa, AreaAtuacao area, List<TelefoneEmpresa> telefone, List<EmailEmpresa> email, Cidade cidade) {
		empresa.setAreaAtuacao(area);
		empresa.setDescricao(descricao);
		empresa.setNome(nome);
		empresa.setTelefoneEmpresa(telefone);
		empresa.setEmailEmpresa(email);
		empresa.getEndereco().setCidade(cidade);
		empresa.getEndereco().setBairro(bairro);
		empresa.getEndereco().setCep(cep);
		empresa.getEndereco().setNumero(numero);
		empresa.getEndereco().setRua(rua);
		return empresa;
	}
}
