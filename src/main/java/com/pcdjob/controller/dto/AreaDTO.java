package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.AreaAtuacao;

public class AreaDTO {
	
	private Long id;
	private String areaAtuacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	public AreaDTO() {
		
	}
	
	public AreaDTO(AreaAtuacao area) {
		this.id = area.getId();
		this.areaAtuacao = area.getAreaAtuacao();
	}
	
	public static Page<AreaDTO> converter(Page<AreaAtuacao> areas) {
		return areas.map(AreaDTO::new);
	}
}
