package com.pcdjob.controller.form;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EnderecoEmpresa;

public class EmpresaForm {
	private String nome;
	private String senha;
	private List<String> email;
	private List<String> telefone;
	private String descricao;
	private Long areaAtuacao;
	private String rua;
	private String cep;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String sigla;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(Long areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
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
	public EmpresaEntity converter(AreaAtuacao area) {
		String novaSenha = new BCryptPasswordEncoder().encode(senha);
		if(descricao != null && !descricao.equals(" ")) {
			return new EmpresaEntity(nome, novaSenha, descricao, area);
		} else {
			return new EmpresaEntity(nome, novaSenha, area);
		}
	}	
	
	public EnderecoEmpresa converterEndereco(EmpresaEntity empresa, Cidade cidade) {
		return new EnderecoEmpresa(rua, numero, bairro, cep, cidade, empresa);
	}
}
