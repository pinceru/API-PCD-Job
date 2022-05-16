package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.controller.dto.CandidatoAtualizadoDTO;
import com.pcdjob.controller.dto.response.ResponseCursoCandidato;
import com.pcdjob.controller.dto.response.ResponseDeficiencia;
import com.pcdjob.controller.dto.response.ResponseEmailCandidato;
import com.pcdjob.controller.dto.response.ResponseExperienciaProfissional;
import com.pcdjob.controller.dto.response.ResponseTelefoneCandidato;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EmailCandidato;
import com.pcdjob.model.candidato.ExperienciaProfissional;
import com.pcdjob.model.candidato.TelefoneCandidato;
import com.pcdjob.service.helper.Verificar;

@Service
public class CandidatoResponseService {

	public List<ResponseEmailCandidato> converterEmailCandidato(CandidatoEntity candidato) {
		if(candidato.getEmailCandidato().size() > 0) {
			int indice = 0;
			List<ResponseEmailCandidato> emailCandidatoList = new ArrayList<>();
			while(indice < candidato.getEmailCandidato().size() && indice < 2) {
				EmailCandidato emailCandidato = candidato.getEmailCandidato().get(indice);
				ResponseEmailCandidato response = new ResponseEmailCandidato(emailCandidato.getId(), emailCandidato.getEmail());
				emailCandidatoList.add(response);
				indice++;
			}
			return emailCandidatoList;
		} else {
			return null;
		}
		
//		Verificar.verificarList(candidato.getEmailCandidato());
//		List<EmailCandidato> emailsCandidato = candidato.getEmailCandidato();
//		List<ResponseEmailCandidato> responseList = new ArrayList<>();
//		while(emailsCandidato.size() < 2) {
//			emailsCandidato.forEach((email) -> {
//				ResponseEmailCandidato response = new ResponseEmailCandidato(email.getId(), email.getEmail());
//				responseList.add(response);
//			});
//		}
//		return responseList;
	}
	
	public List<ResponseTelefoneCandidato> converterTelefoneCandidato(CandidatoEntity candidato) {
		if(candidato.getTelefoneCandidato().size() > 0) {
			int indice = 0;
			List<ResponseTelefoneCandidato> telefoneCandidatoList = new ArrayList<>();
			while(indice < candidato.getTelefoneCandidato().size() && indice < 2) {
				TelefoneCandidato telefoneCandidato = candidato.getTelefoneCandidato().get(indice);
				ResponseTelefoneCandidato response = new ResponseTelefoneCandidato(telefoneCandidato.getId(), telefoneCandidato.getNumero());
				telefoneCandidatoList.add(response);
				indice++;
			}
			return telefoneCandidatoList;
		} else {
			return null;
		}
	}
	
	public List<ResponseDeficiencia> converterDeficienciaCandidato(CandidatoEntity candidato) {
		if(candidato.getDeficienciaCandidato().size() > 0) {
			int indice = 0;
			List<ResponseDeficiencia> deficienciaCandidatoList = new ArrayList<>();
			while(indice < candidato.getDeficienciaCandidato().size()) {
				Deficiencia deficienciaCandidato = candidato.getDeficienciaCandidato().get(indice).getDeficiencia();
				ResponseDeficiencia response = 
						new ResponseDeficiencia(deficienciaCandidato.getId(), deficienciaCandidato.getTipoDeficiencia().getId(), 
								deficienciaCandidato.getDeficiencia(), deficienciaCandidato.getTipoDeficiencia().getTipo(), candidato.getDeficienciaCandidato().get(indice).getId());
				deficienciaCandidatoList.add(response);
				indice++;
			}
			return  deficienciaCandidatoList;
		} else {
			return null;
		}
	}
	
	public List<ResponseExperienciaProfissional> converterExperienciaProfissional(CandidatoEntity candidato) {
		if(candidato.getExperienciaProfissional().size() > 0) {
			int indice = 0;
			List<ResponseExperienciaProfissional> experienciaList = new ArrayList<>();
			while(indice < candidato.getExperienciaProfissional().size()) {
				ExperienciaProfissional exp = candidato.getExperienciaProfissional().get(indice);
				ResponseExperienciaProfissional experienciaCandidato = 
						new ResponseExperienciaProfissional(exp.getId(), exp.getCargo(), exp.getDataInicio(), exp.getDataSaida(), exp.getAtribuicoes(), exp.getNomeEmpresa());
				experienciaList.add(experienciaCandidato);
				indice++;
			}
			return experienciaList;
		} else {
			return null;
		}
	}
	
	public List<ResponseCursoCandidato> converterCursoCandidato(CandidatoEntity candidato) {
		if(candidato.getCursoCandidato().size() > 0) {
			int indice = 0;
			List<ResponseCursoCandidato> cursoList = new ArrayList<>();
			while(indice < candidato.getCursoCandidato().size()) {
				Curso cursos = candidato.getCursoCandidato().get(indice).getCurso();
				ResponseCursoCandidato cursoCandidato = 
						new ResponseCursoCandidato(cursos.getId(), cursos.getCurso(), cursos.getNivel().getId(), 
								cursos.getNivel().getNivel(), cursos.getAreaAtuacao().getId(), cursos.getAreaAtuacao().getAreaAtuacao(), candidato.getCursoCandidato().get(indice).getId());
				cursoList.add(cursoCandidato);
				indice++;
			}
			return cursoList;
		} else {
			return null;
		}
	}
	
	public List<CandidatoAtualizadoDTO> listarCandidatos(List<CandidatoEntity> candidatos) {
		int indice = 0;
		List<CandidatoAtualizadoDTO> dtoList = new ArrayList<>();
		while(candidatos.size() > indice) {
			List<ResponseExperienciaProfissional> experiencias = converterExperienciaProfissional(candidatos.get(indice));
			List<ResponseDeficiencia> deficiencias = converterDeficienciaCandidato(candidatos.get(indice));
			List<ResponseCursoCandidato> cursos = converterCursoCandidato(candidatos.get(indice));
			List<ResponseEmailCandidato> emails = converterEmailCandidato(candidatos.get(indice));
			List<ResponseTelefoneCandidato> telefones = converterTelefoneCandidato(candidatos.get(indice));
			
			CandidatoAtualizadoDTO dto = new CandidatoAtualizadoDTO(candidatos.get(indice), emails, telefones, deficiencias, experiencias, cursos);
			dtoList.add(dto);
			indice++;
		}
		return dtoList;
	}
	
	public Page<CandidatoAtualizadoDTO> paginarCandidatosDTO(List<CandidatoAtualizadoDTO> dtos, Pageable paginacao) {
		Page<CandidatoAtualizadoDTO> paginasDTO = new PageImpl<CandidatoAtualizadoDTO>(dtos, paginacao, dtos.size());
		return paginasDTO;
	}
}
