
  
package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.SuportePCD;

public class SuporteDTO {
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
	
	public SuporteDTO(SuportePCD suporte) {
		this.id = suporte.getId();
		this.nome = suporte.getSuporte();
	}
	
	public static Page<SuporteDTO> converter(Page<SuportePCD> suportes) {
		return suportes.map(SuporteDTO::new);
	}
}