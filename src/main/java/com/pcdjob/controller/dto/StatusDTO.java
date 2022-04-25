package com.pcdjob.controller.dto;

import org.springframework.data.domain.Page;

import com.pcdjob.model.vaga.StatusVaga;

public class StatusDTO {
	private Long id;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public StatusDTO(StatusVaga status) {
		this.id = status.getId();
		this.status = status.getStatus();
	}
	
	public static Page<StatusDTO> converter(Page<StatusVaga> statusVaga) {
		return statusVaga.map(StatusDTO::new);
	}
}
