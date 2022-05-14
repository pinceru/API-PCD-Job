package com.pcdjob.model.vaga;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pcdjob.model.empresa.EmpresaEntity;

@Entity
@Table(name = "tbl_vaga")
public class VagaEntity {
	@Id
	@Column(name = "id_vaga")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int status;
	private String titulo;
	private String descricao;
	private String requisitos;
	@ManyToOne
	private TipoContrato tipoContrato;
	@ManyToOne
	private Salario salario;
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
	private List<VagaDeficiencia> vagaDeficiencia;
	@ManyToOne
	private Horario horario;
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
	private List<VagaSuportePCD> vagaSuporte;
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
	private List<VagaBeneficio> vagaBeneficio;
	@ManyToOne
	private EmpresaEntity empresa;
	@ManyToOne
	private LocalTrabalho localTrabalho;
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
	private List<FormacaoDesejada> formacaoDesejada;
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
	private List<VagaCandidato> vagaCandidato;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public Salario getSalario() {
		return salario;
	}
	public void setSalario(Salario salario) {
		this.salario = salario;
	}
	public List<VagaDeficiencia> getVagaDeficiencia() {
		return vagaDeficiencia;
	}
	public void setVagaDeficiencia(List<VagaDeficiencia> vagaDeficiencia) {
		this.vagaDeficiencia = vagaDeficiencia;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	public List<VagaSuportePCD> getVagaSuporte() {
		return vagaSuporte;
	}
	public void setVagaSuporte(List<VagaSuportePCD> vagaSuporte) {
		this.vagaSuporte = vagaSuporte;
	}
	public List<VagaBeneficio> getVagaBeneficio() {
		return vagaBeneficio;
	}
	public void setVagaBeneficio(List<VagaBeneficio> vagaBeneficio) {
		this.vagaBeneficio = vagaBeneficio;
	}
	public EmpresaEntity getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}
	public LocalTrabalho getLocalTrabalho() {
		return localTrabalho;
	}
	public void setLocalTrabalho(LocalTrabalho localTrabalho) {
		this.localTrabalho = localTrabalho;
	}
	public List<FormacaoDesejada> getFormacaoDesejada() {
		return formacaoDesejada;
	}
	public void setFormacaoDesejada(List<FormacaoDesejada> formacaoDesejada) {
		this.formacaoDesejada = formacaoDesejada;
	}
	public List<VagaCandidato> getVagaCandidato() {
		return vagaCandidato;
	}
	public void setVagaCandidato(List<VagaCandidato> vagaCandidato) {
		this.vagaCandidato = vagaCandidato;
	}
	public VagaEntity() {
		
	}
	public VagaEntity(int status, String titulo, String descricao, String requisitos, TipoContrato tipoContrato, Horario horario, EmpresaEntity empresa, LocalTrabalho localTrabalho, Salario salario) {
		this.status = status;
		this.titulo = titulo;
		this.descricao = descricao;
		this.requisitos = requisitos;
		this.tipoContrato = tipoContrato;
		this.horario = horario;
		this.empresa = empresa;
		this.localTrabalho = localTrabalho;
		this.salario = salario;
	}
}
