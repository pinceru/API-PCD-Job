package com.pcdjob.controller.dto;

import com.pcdjob.model.empresa.EmpresaEntity;

public class EmpresaDTO {
	private Long id;
	private String nome;
	
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
	
	public EmpresaDTO(EmpresaEntity empresa) {
		this.id = empresa.getId();
		this.nome = empresa.getNome();
	}
}
