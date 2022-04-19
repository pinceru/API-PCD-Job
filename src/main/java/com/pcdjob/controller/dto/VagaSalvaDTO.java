package com.pcdjob.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.pcdjob.controller.dto.response.ResponseBeneficio;
import com.pcdjob.controller.dto.response.ResponseDeficiencia;
import com.pcdjob.controller.dto.response.ResponseEmpresa;
import com.pcdjob.controller.dto.response.ResponseFormacao;
import com.pcdjob.controller.dto.response.ResponseHorario;
import com.pcdjob.controller.dto.response.ResponseLocalTrabalho;
import com.pcdjob.controller.dto.response.ResponseSalario;
import com.pcdjob.controller.dto.response.ResponseSuporte;
import com.pcdjob.controller.dto.response.ResponseTipoContrato;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.vaga.Beneficio;
import com.pcdjob.model.vaga.LocalTrabalho;
import com.pcdjob.model.vaga.VagaEntity;

public class VagaSalvaDTO {

	private Long id;
	private int status;
	private String titulo;
	private String descricao;
	private String requisitos;
	private ResponseTipoContrato tipoContrato;
	private ResponseSalario salario;
	private List<ResponseSuporte> suporte;
	private List<ResponseBeneficio> beneficio;
	private List<ResponseFormacao> formacaoDesejada;
	private List<ResponseDeficiencia> deficiencia;
	private ResponseLocalTrabalho localTrabalho;
	private ResponseHorario horario;
	private ResponseEmpresa empresa;
	
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

	public ResponseTipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(ResponseTipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public ResponseSalario getSalario() {
		return salario;
	}

	public void setSalario(ResponseSalario salario) {
		this.salario = salario;
	}

	public List<ResponseSuporte> getSuporte() {
		return suporte;
	}

	public void setSuporte(List<ResponseSuporte> suporte) {
		this.suporte = suporte;
	}

	public List<ResponseBeneficio> getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(List<ResponseBeneficio> beneficio) {
		this.beneficio = beneficio;
	}

	public List<ResponseFormacao> getFormacaoDesejada() {
		return formacaoDesejada;
	}

	public void setFormacaoDesejada(List<ResponseFormacao> formacaoDesejada) {
		this.formacaoDesejada = formacaoDesejada;
	}

	public List<ResponseDeficiencia> getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(List<ResponseDeficiencia> deficiencia) {
		this.deficiencia = deficiencia;
	}

	public ResponseLocalTrabalho getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(ResponseLocalTrabalho localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public ResponseHorario getHorario() {
		return horario;
	}

	public void setHorario(ResponseHorario horario) {
		this.horario = horario;
	}
	public ResponseEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ResponseEmpresa empresa) {
		this.empresa = empresa;
	}

	public VagaSalvaDTO(VagaEntity vaga) {
		LocalTrabalho local = vaga.getLocalTrabalho();
		
		this.id = vaga.getId();
		this.status = vaga.getStatus();
		this.titulo = vaga.getTitulo();
		this.descricao = vaga.getDescricao();
		this.requisitos = vaga.getRequisitos();
		this.localTrabalho
			= new ResponseLocalTrabalho(local.getId(), local.getRua(), local.getNumero(), local.getBairro(), local.getCidade().getCidade(), local.getCidade().getId(),
				local.getCidade().getEstado().getEstado(), local.getCidade().getEstado().getSigla(), local.getCidade().getEstado().getId(), local.getCep());
		this.horario = new ResponseHorario(vaga.getHorario().getId(), vaga.getHorario().getHorarioInicio(), vaga.getHorario().getHorarioSaida(), vaga.getHorario().getVisivel());
		this.tipoContrato = new ResponseTipoContrato(vaga.getTipoContrato().getId(), vaga.getTipoContrato().getTipoContrato());
		this.salario = new ResponseSalario(vaga.getSalario().getId(), vaga.getSalario().getSalario(), vaga.getSalario().getVisivel());
		if(vaga.getVagaSuporte().size() > 0) {
			int indice = 0;
			List<ResponseSuporte> suporteList = new ArrayList<>();
			while(indice < vaga.getVagaSuporte().size()) {
				SuportePCD suporteVaga = vaga.getVagaSuporte().get(indice).getSuporte();
				ResponseSuporte response = new ResponseSuporte(suporteVaga.getId(), suporteVaga.getSuporte());
				suporteList.add(response);
				indice++;
			}
			this.suporte = suporteList;
		}
		if(vaga.getVagaBeneficio().size() > 0) {
			int indice = 0;
			List<ResponseBeneficio> beneficioList = new ArrayList<>();
			while(indice < vaga.getVagaBeneficio().size()) {
				Beneficio beneficioVaga = vaga.getVagaBeneficio().get(indice).getBeneficio();
				ResponseBeneficio response = new ResponseBeneficio(beneficioVaga.getId(), beneficioVaga.getBeneficio(), vaga.getVagaBeneficio().get(indice).getId());
				beneficioList.add(response);
				indice++;
			}
			this.beneficio = beneficioList;
		}
		if(vaga.getFormacaoDesejada().size() > 0) {
			int indice = 0;
			List<ResponseFormacao> cursoList = new ArrayList<>();
			while(indice < vaga.getFormacaoDesejada().size()) {
				Curso curso = vaga.getFormacaoDesejada().get(indice).getCurso();
				ResponseFormacao response = new ResponseFormacao(curso.getId(), curso.getCurso(), curso.getAreaAtuacao().getId(), curso.getAreaAtuacao().getAreaAtuacao(), vaga.getFormacaoDesejada().get(indice).getId());
				cursoList.add(response);
				indice++;
			}
			this.formacaoDesejada = cursoList;
		}
		if(vaga.getVagaDeficiencia().size() > 0) {
			int indice = 0;
			List<ResponseDeficiencia> deficienciaList = new ArrayList<>();
			while(indice < vaga.getVagaDeficiencia().size()) {
				Deficiencia deficiencia = vaga.getVagaDeficiencia().get(indice).getDeficiencia();
				ResponseDeficiencia response =
						new ResponseDeficiencia(deficiencia.getId(), deficiencia.getTipoDeficiencia().getId(), deficiencia.getDeficiencia(),
								deficiencia.getTipoDeficiencia().getTipo(), vaga.getVagaDeficiencia().get(indice).getId());
				deficienciaList.add(response);
				indice++;
			}
			this.deficiencia = deficienciaList;
		}
		this.empresa = new ResponseEmpresa(vaga.getEmpresa().getId(), vaga.getEmpresa().getNome());
	}
	
	public static Page<VagaSalvaDTO> converter(Page<VagaEntity> vagas) {
		return vagas.map(VagaSalvaDTO::new);
	}

}
