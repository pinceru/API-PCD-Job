package com.pcdjob.model.vaga;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_status_vaga")
public class StatusVaga {
	@Id
	@Column(name = "id_status_vaga")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	@OneToMany(mappedBy = "status")
	private List<VagaCandidato> vaga;
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
	public List<VagaCandidato> getVaga() {
		return vaga;
	}
	public void setVaga(List<VagaCandidato> vaga) {
		this.vaga = vaga;
	}
}
