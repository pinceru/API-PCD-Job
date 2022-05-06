package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.controller.dto.VagaSalvaDTO;
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

@Service
public class VagaResponseService {
	public ResponseLocalTrabalho converterLocal(VagaEntity vaga) {
		LocalTrabalho local = vaga.getLocalTrabalho();
		return new ResponseLocalTrabalho(local.getId(), local.getRua(), local.getNumero(), local.getBairro(), local.getCidade().getCidade(), local.getCidade().getId(),
				local.getCidade().getEstado().getEstado(), local.getCidade().getEstado().getSigla(), local.getCidade().getEstado().getId(), local.getCep());
	}
	
	public ResponseHorario converterHorario(VagaEntity vaga) {
		return new ResponseHorario(vaga.getHorario().getId(), vaga.getHorario().getHorarioInicio(), vaga.getHorario().getHorarioSaida(), vaga.getHorario().getVisivel());
	}
	
	public ResponseTipoContrato converterTipoContrato(VagaEntity vaga) {
		return new ResponseTipoContrato(vaga.getTipoContrato().getId(), vaga.getTipoContrato().getTipoContrato());
	}
	
	public ResponseSalario converterSalario(VagaEntity vaga) {
		return new ResponseSalario(vaga.getSalario().getId(), vaga.getSalario().getSalario(), vaga.getSalario().getVisivel());
	}
	
	public List<ResponseSuporte> converterSuportes(VagaEntity vaga) {
		if(vaga.getVagaSuporte().size() > 0) {
			int indice = 0;
			List<ResponseSuporte> suporteList = new ArrayList<>();
			while(indice < vaga.getVagaSuporte().size()) {
				SuportePCD suporteVaga = vaga.getVagaSuporte().get(indice).getSuporte();
				ResponseSuporte response = new ResponseSuporte(suporteVaga.getId(), suporteVaga.getSuporte());
				suporteList.add(response);
				indice++;
			}
			return suporteList;
		} else {
			return null;
		}
	}
	
	public List<ResponseBeneficio> converterBeneficio(VagaEntity vaga) {
		if(vaga.getVagaBeneficio().size() > 0) {
			int indice = 0;
			List<ResponseBeneficio> beneficioList = new ArrayList<>();
			while(indice < vaga.getVagaBeneficio().size()) {
				Beneficio beneficioVaga = vaga.getVagaBeneficio().get(indice).getBeneficio();
				ResponseBeneficio response = new ResponseBeneficio(beneficioVaga.getId(), beneficioVaga.getBeneficio(), vaga.getVagaBeneficio().get(indice).getId());
				beneficioList.add(response);
				indice++;
			}
			return beneficioList;
		} else {
			return null;
		}
	}
	
	public List<ResponseFormacao> converterFormacao(VagaEntity vaga) {
		if(vaga.getFormacaoDesejada().size() > 0) {
			int indice = 0;
			List<ResponseFormacao> cursoList = new ArrayList<>();
			while(indice < vaga.getFormacaoDesejada().size()) {
				Curso curso = vaga.getFormacaoDesejada().get(indice).getCurso();
				ResponseFormacao response = new ResponseFormacao(curso.getId(), curso.getCurso(), curso.getAreaAtuacao().getId(), curso.getAreaAtuacao().getAreaAtuacao(), vaga.getFormacaoDesejada().get(indice).getId());
				cursoList.add(response);
				indice++;
			}
			return cursoList;
		} else {
			return null;
		}
	}
	
	public List<ResponseDeficiencia> converterDeficiencia(VagaEntity vaga) {
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
			return deficienciaList;
		} else {
			return null;
		}
	}
	
	public ResponseEmpresa converterEmpresa(VagaEntity vaga) {
		return new ResponseEmpresa(vaga.getEmpresa().getId(), vaga.getEmpresa().getNome());
	}
	
	public List<VagaSalvaDTO> listarVagas(List<VagaEntity> vaga) {
		int indice = 0;
		List<VagaSalvaDTO> dtoList = new ArrayList<>();
		while(vaga.size() > indice) {
			List<ResponseSuporte> suportes = converterSuportes(vaga.get(indice));
			List<ResponseBeneficio> beneficios = converterBeneficio(vaga.get(indice));
			List<ResponseDeficiencia> deficiencias = converterDeficiencia(vaga.get(indice));
			List<ResponseFormacao> formacao = converterFormacao(vaga.get(indice));
			ResponseEmpresa empresa = converterEmpresa(vaga.get(indice));
			ResponseHorario horario = converterHorario(vaga.get(indice));
			ResponseSalario salario = converterSalario(vaga.get(indice));
			ResponseTipoContrato contrato = converterTipoContrato(vaga.get(indice));
			ResponseLocalTrabalho localTrabalho = converterLocal(vaga.get(indice));
			
			VagaSalvaDTO dto = new VagaSalvaDTO(vaga.get(indice), localTrabalho, horario, contrato, salario, suportes, beneficios, formacao, deficiencias, empresa);
			dtoList.add(dto);
			indice++;
		}
		return dtoList;
	}
	
	public Page<VagaSalvaDTO> paginarVagasDTO(List<VagaSalvaDTO> dtos, Pageable paginacao) {
		Page<VagaSalvaDTO> paginasDTO = new PageImpl<VagaSalvaDTO>(dtos, paginacao, dtos.size());
		return paginasDTO;
	}
}
