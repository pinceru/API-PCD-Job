package com.pcdjob.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.pcdjob.controller.EnderecoCandidatoDTO;
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

public class CandidatoAtualizadoDTO {
	private Long id;
	private String nome;
	private String nomeSocial;
	private String genero;
	private String dataNascimento;
	private String informacoes;
	private List<ResponseEmailCandidato> email;
	private List<ResponseTelefoneCandidato> telefone;
	private List<ResponseDeficiencia> deficiencia;
	private List<ResponseExperienciaProfissional> experiencia;
	private List<ResponseCursoCandidato> curso;
	private EnderecoCandidatoDTO endereco;
	
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


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}
	

	public String getNomeSocial() {
		return nomeSocial;
	}


	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}


	public List<ResponseEmailCandidato> getEmail() {
		return email;
	}


	public void setEmail(List<ResponseEmailCandidato> email) {
		this.email = email;
	}

	public List<ResponseTelefoneCandidato> getTelefone() {
		return telefone;
	}


	public void setTelefone(List<ResponseTelefoneCandidato> telefone) {
		this.telefone = telefone;
	}

	
	public String getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	public String getInformacoes() {
		return informacoes;
	}


	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public List<ResponseDeficiencia> getDeficiencia() {
		return deficiencia;
	}


	public void setDeficiencia(List<ResponseDeficiencia> deficiencia) {
		this.deficiencia = deficiencia;
	}

	
	public List<ResponseExperienciaProfissional> getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(List<ResponseExperienciaProfissional> experiencia) {
		this.experiencia = experiencia;
	}

	

	public List<ResponseCursoCandidato> getCurso() {
		return curso;
	}


	public void setCurso(List<ResponseCursoCandidato> curso) {
		this.curso = curso;
	}

	
	
	public EnderecoCandidatoDTO getEndereco() {
		return endereco;
	}


	public void setEndereco(EnderecoCandidatoDTO endereco) {
		this.endereco = endereco;
	}


	public CandidatoAtualizadoDTO(CandidatoEntity candidato) {
			
		this.id = candidato.getId();
		this.nome = candidato.getNome();
		this.nomeSocial = candidato.getNomeSocial();
		this.genero = candidato.getGenero().getGenero();
		this.dataNascimento = candidato.getDataNascimento();
		this.informacoes = candidato.getInformacoes();
	
		if(candidato.getEmailCandidato().size() > 0) {
			int indice = 0;
			List<ResponseTelefoneCandidato> telefoneCandidatoList = new ArrayList<>();
			while(indice < candidato.getTelefoneCandidato().size() && indice < 2) {
				TelefoneCandidato telefoneCandidato = candidato.getTelefoneCandidato().get(indice);
				ResponseTelefoneCandidato response = new ResponseTelefoneCandidato(telefoneCandidato.getId(), telefoneCandidato.getNumero());
				telefoneCandidatoList.add(response);
				indice++;
			}
			this.telefone = telefoneCandidatoList;
		} 
		
		if(candidato.getEmailCandidato().size() > 0) {
			int indice = 0;
			List<ResponseEmailCandidato> emailCandidatoList = new ArrayList<>();
			while(indice < candidato.getEmailCandidato().size() && indice < 2) {
				EmailCandidato emailCandidato = candidato.getEmailCandidato().get(indice);
				ResponseEmailCandidato response = new ResponseEmailCandidato(emailCandidato.getId(), emailCandidato.getEmail());
				emailCandidatoList.add(response);
				indice++;
			}
			this.email = emailCandidatoList;
		} 
		
		if(candidato.getDeficienciaCandidato().size() > 0) {
			int indice = 0;
			List<ResponseDeficiencia> deficienciaCandidatoList = new ArrayList<>();
			while(indice < candidato.getDeficienciaCandidato().size()) {
				Deficiencia deficienciaCandidato = candidato.getDeficienciaCandidato().get(indice).getDeficiencia();
				ResponseDeficiencia response = 
						new ResponseDeficiencia(deficienciaCandidato.getId(), deficienciaCandidato.getTipoDeficiencia().getId(), deficienciaCandidato.getDeficiencia(), deficienciaCandidato.getTipoDeficiencia().getTipo());
				deficienciaCandidatoList.add(response);
				indice++;
			}
			this.deficiencia = deficienciaCandidatoList;
		} 
		
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
			this.experiencia = experienciaList;
		} 
		
		if(candidato.getCursoCandidato().size() > 0) {
			int indice = 0;
			List<ResponseCursoCandidato> cursoList = new ArrayList<>();
			while(indice < candidato.getCursoCandidato().size()) {
				Curso cursos = candidato.getCursoCandidato().get(indice).getCurso();
				ResponseCursoCandidato cursoCandidato = 
						new ResponseCursoCandidato(cursos.getId(), cursos.getCurso(), cursos.getNivel().getId(), cursos.getNivel().getNivel(), cursos.getAreaAtuacao().getId(), cursos.getAreaAtuacao().getAreaAtuacao());
				cursoList.add(cursoCandidato);
				indice++;
			}
			this.curso = cursoList;
		}
		
		this.endereco = new EnderecoCandidatoDTO(candidato.getEndereco());

	}


	public static Page<CandidatoAtualizadoDTO> converter(Page<CandidatoEntity> candidatos) {
		return candidatos.map(CandidatoAtualizadoDTO::new);
	}
}
