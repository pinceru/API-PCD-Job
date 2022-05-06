package com.pcdjob.controller.dto;

import java.util.List;

import com.pcdjob.controller.dto.response.ResponseEmailEmpresa;
import com.pcdjob.controller.dto.response.ResponseEnderecoEmpresa;
import com.pcdjob.controller.dto.response.ResponseTelefoneEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;

public class EmpresaSalvaDTO {
	private Long id;
	private String nome;
	private String descricao;
	private List<ResponseEmailEmpresa> email;
	private List<ResponseTelefoneEmpresa> telefone;
	private AreaDTO areaAtuacao;
	private ResponseEnderecoEmpresa endereco;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ResponseEmailEmpresa> getEmail() {
		return email;
	}

	public void setEmail(List<ResponseEmailEmpresa> email) {
		this.email = email;
	}

	public List<ResponseTelefoneEmpresa> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<ResponseTelefoneEmpresa> telefone) {
		this.telefone = telefone;
	}

	public AreaDTO getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaDTO areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public ResponseEnderecoEmpresa getEndereco() {
		return endereco;
	}

	public void setEndereco(ResponseEnderecoEmpresa endereco) {
		this.endereco = endereco;
	}
	
	
	public EmpresaSalvaDTO(EmpresaEntity empresa, List<ResponseEmailEmpresa> responseEmail,
			List<ResponseTelefoneEmpresa> responseTelefone, ResponseEnderecoEmpresa responseEndereco, AreaDTO area) {
		this.id = empresa.getId();
		this.nome = empresa.getNome();
		if(empresa.getDescricao() != " ") {
			this.descricao = empresa.getDescricao();
		}
		this.email = responseEmail;
		this.telefone = responseTelefone;
		this.areaAtuacao = area;
		this.endereco = responseEndereco;
	}

}
