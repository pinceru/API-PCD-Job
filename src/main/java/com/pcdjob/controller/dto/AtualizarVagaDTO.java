package com.pcdjob.controller.dto;

import java.util.List;
import java.util.Optional;

import com.pcdjob.model.Curso;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.vaga.Beneficio;
import com.pcdjob.model.vaga.FormacaoDesejada;
import com.pcdjob.model.vaga.Salario;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaBeneficio;
import com.pcdjob.model.vaga.VagaDeficiencia;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.model.vaga.VagaSuportePCD;
import com.pcdjob.repository.BeneficioRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.FormacaoDesejadaRepository;
import com.pcdjob.repository.HorarioRepository;
import com.pcdjob.repository.SalarioRepository;
import com.pcdjob.repository.SuporteRepository;
import com.pcdjob.repository.VagaBeneficioRepository;
import com.pcdjob.repository.VagaDeficienciaRepository;
import com.pcdjob.repository.VagaRepository;
import com.pcdjob.repository.VagaSuporteRepository;
import com.pcdjob.service.ConverterEnderecoEHorario;

public class AtualizarVagaDTO {
	private int status;
	private String titulo;
	private String descricao;
	private String requisitos;
	private Long tipoContrato;
	private String salario;
	private int statusSalario;
	private List<Long> suporte;
	private List<Long> beneficio;
	private List<Long> formacaoDesejada;
	private List<Long> deficiencia;
	private String rua;
	private String cidade;
	private String estado;
	private String sigla;
	private String bairro;
	private String numero;
	private String cep;
	private String horarioInicio;
	private String horarioSaida;
	private int statusHorario;
	
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
	public Long getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(Long tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public List<Long> getSuporte() {
		return suporte;
	}
	public void setSuporte(List<Long> suporte) {
		this.suporte = suporte;
	}
	public List<Long> getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(List<Long> beneficio) {
		this.beneficio = beneficio;
	}
	public List<Long> getFormacaoDesejada() {
		return formacaoDesejada;
	}
	public void setFormacaoDesejada(List<Long> formacaoDesejada) {
		this.formacaoDesejada = formacaoDesejada;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
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
	public int getStatusHorario() {
		return statusHorario;
	}
	public void setStatusHorario(int statusHorario) {
		this.statusHorario = statusHorario;
	}
	public List<Long> getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(List<Long> deficiencia) {
		this.deficiencia = deficiencia;
	}
	public int getStatusSalario() {
		return statusSalario;
	}
	public void setStatusSalario(int statusSalario) {
		this.statusSalario = statusSalario;
	}
	
	public void atualizarFormacaoDesejada(VagaEntity vaga, CursoRepository cursoRepository, FormacaoDesejadaRepository formacaoRepository) {
		int indice = 0;
		while(indice < formacaoDesejada.size()) {
			Optional<Curso> cursoObj = cursoRepository.findById(formacaoDesejada.get(indice));
			Optional<FormacaoDesejada> optional = formacaoRepository.findByCursoAndVaga(cursoObj.get(), vaga);
			if(optional.isPresent() != true) {
				formacaoRepository.save(new FormacaoDesejada(vaga, cursoObj.get()));
			} else {
				List<Curso> cursos = cursoRepository.findAll();
				int indice2 = 0;
				while(indice2 < cursos.size()) {
					if(!formacaoDesejada.contains(cursos.get(indice2).getId())) {
						Optional<FormacaoDesejada> cursoOptional = formacaoRepository.findByCurso(cursos.get(indice));
						if(cursoOptional.isPresent()) {
							FormacaoDesejada formacao = cursoOptional.get();
							formacaoRepository.delete(formacao);
						}
					}
					indice2++;
				}
			}
			indice += 1;
		}
	}
	
	public void atualizarBeneficiosVaga(VagaEntity vaga, BeneficioRepository beneficioRepository, VagaBeneficioRepository vagaBeneficioRepository) {
		int indice = 0;
		while(indice < beneficio.size()) {
			Optional<Beneficio> beneficioObj = beneficioRepository.findById(beneficio.get(indice));
			Optional<VagaBeneficio> optional = vagaBeneficioRepository.findByBeneficioAndVaga(beneficioObj.get(), vaga);
			if(optional.isPresent() != true) {
				vagaBeneficioRepository.save(new VagaBeneficio(vaga, beneficioObj.get()));
			} else {
				List<Beneficio> beneficios = beneficioRepository.findAll();
				int indice2 = 0;
				while(indice2 < beneficios.size()) {
					if(!beneficio.contains(beneficios.get(indice2).getId())) {
						Optional<VagaBeneficio> beneficioOptional = vagaBeneficioRepository.findByBeneficio(beneficios.get(indice));
						if(beneficioOptional.isPresent()) {
							VagaBeneficio vagaBeneficio = beneficioOptional.get();
							vagaBeneficioRepository.delete(vagaBeneficio);
						}
					}
					indice2++;
				}
			}
			indice += 1;
		}
	}
	
	public void atualizarDeficienciasVaga(VagaEntity vaga, DeficienciaRepository deficienciaRepository, VagaDeficienciaRepository vagaDeficienciaRepository) {
		int indice = 0;
		while(indice < deficiencia.size()) {
			Optional<Deficiencia> deficienciaObj = deficienciaRepository.findById(deficiencia.get(indice));
			Optional<VagaDeficiencia> optional = vagaDeficienciaRepository.findByDeficienciaAndVaga(deficienciaObj.get(), vaga);
			if(optional.isPresent() != true) {
				vagaDeficienciaRepository.save(new VagaDeficiencia(vaga, deficienciaObj.get()));
			} else {
				List<Deficiencia> deficiencias = deficienciaRepository.findAll();
				int indice2 = 0;
				while(indice2 < deficiencias.size()) {
					if(!deficiencia.contains(deficiencias.get(indice2).getId())) {
						Optional<VagaDeficiencia> deficienciaOptional = vagaDeficienciaRepository.findByDeficiencia(deficiencias.get(indice));
						if(deficienciaOptional.isPresent()) {
							VagaDeficiencia vagaDeficiencia = deficienciaOptional.get();
							vagaDeficienciaRepository.delete(vagaDeficiencia);
						}
					}
					indice2++;
				}
			}
			indice += 1;
		}
	}
	
	public void atualizarSuportesVaga(VagaEntity vaga, SuporteRepository suporteRepository, VagaSuporteRepository vagaSuporteRepository) {
		int indice = 0;
		while(indice < suporte.size()) {
			Optional<SuportePCD> suporteObj = suporteRepository.findById(suporte.get(indice));
			Optional<VagaSuportePCD> optional = vagaSuporteRepository.findBySuporteAndVaga(suporteObj.get(), vaga);
			if(optional.isPresent() != true) {
				vagaSuporteRepository.save(new VagaSuportePCD(vaga, suporteObj.get()));
			} else {
				List<SuportePCD> suportes = suporteRepository.findAll();
				int indice2 = 0;
				while(indice2 < suportes.size()) {
					if(!suporte.contains(suportes.get(indice2).getId())) {
						Optional<VagaSuportePCD> suporteOptional = vagaSuporteRepository.findBySuporte(suportes.get(indice));
						if(suporteOptional.isPresent()) {
							VagaSuportePCD vagaSuporte = suporteOptional.get();
							vagaSuporteRepository.delete(vagaSuporte);
						}
					}
					indice2++;
				}
			}
			indice += 1;
		}
	}
	
	public TipoContrato converterTipoContrato(TipoContratoRepository tipoContratoRepository) {
		return tipoContratoRepository.getOne(tipoContrato);
	}
	
	public Salario converterSalario(SalarioRepository salarioRepository) {
		Optional<Salario> optional = salarioRepository.findBySalarioAndVisivel(salario, statusSalario);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return salarioRepository.save(new Salario(salario, statusSalario));
		}
	}
	
	public VagaEntity converter(Long id, VagaRepository vagaRepository, SalarioRepository salarioRepository, TipoContratoRepository tipoContratoRepository, HorarioRepository horarioRepository) {
		VagaEntity vaga = vagaRepository.getOne(id);
		ConverterEnderecoEHorario conversor = new ConverterEnderecoEHorario();
		vaga.setDescricao(descricao);
		vaga.setStatus(status);
		vaga.setTitulo(titulo);
		vaga.setSalario(converterSalario(salarioRepository));
		vaga.setTipoContrato(converterTipoContrato(tipoContratoRepository));
		vaga.setHorario(conversor.horario(horarioInicio, horarioSaida, status, horarioRepository));
		return vaga;
	}
}
