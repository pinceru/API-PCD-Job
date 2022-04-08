package com.pcdjob.model.vaga;

import java.util.List;

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
	@OneToMany(mappedBy = "vaga")
	private List<VagaSalario> vagaSalario;
	@OneToMany(mappedBy = "vaga")
	private List<VagaDeficiencia> vagaDeficiencia;
	@ManyToOne
	private Horario horario;
	@OneToMany(mappedBy = "vaga")
	private List<VagaSuportePCD> vagaSuporte;
	@OneToMany(mappedBy = "vaga")
	private List<VagaBeneficio> vagaBeneficio;
	@ManyToOne
	private EmpresaEntity empresa;
	@ManyToOne
	private LocalTrabalho localTrabalho;
	@OneToMany(mappedBy = "vaga")
	private List<FormacaoDesejada> formacaoDesejada;
	
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
	public List<VagaSalario> getVagaSalario() {
		return vagaSalario;
	}
	public void setVagaSalario(List<VagaSalario> vagaSalario) {
		this.vagaSalario = vagaSalario;
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
	
}
