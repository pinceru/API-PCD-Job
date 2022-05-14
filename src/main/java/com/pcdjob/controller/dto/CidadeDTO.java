package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.Cidade;

public class CidadeDTO {
	private Long id;
	private String cidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public CidadeDTO(Cidade cidade) {
		this.id = cidade.getId();
		this.cidade = cidade.getCidade();
	}
	
	public static Page<CidadeDTO> converter(Page<Cidade> cidade) {
		return cidade.map(CidadeDTO::new);
	}
}
