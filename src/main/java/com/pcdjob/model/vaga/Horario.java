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
@Table(name = "tbl_horario")
public class Horario {
	@Id
	@Column(name = "id_horario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String horarioInicio;
	private String horarioSaida;
	private String cargaHoraria;
	private int visivel;
	@OneToMany(mappedBy = "horario")
	private List<VagaEntity> vaga;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioSaida() {
		return horarioSaida;
	}
	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public int getVisivel() {
		return visivel;
	}
	public void setVisivel(int visivel) {
		this.visivel = visivel;
	}
	public List<VagaEntity> getVaga() {
		return vaga;
	}
	public void setVaga(List<VagaEntity> vaga) {
		this.vaga = vaga;
	}
}
