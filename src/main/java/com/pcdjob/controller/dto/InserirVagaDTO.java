package com.pcdjob.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.Beneficio;
import com.pcdjob.model.vaga.FormacaoDesejada;
import com.pcdjob.model.vaga.Horario;
import com.pcdjob.model.vaga.LocalTrabalho;
import com.pcdjob.model.vaga.Salario;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaBeneficio;
import com.pcdjob.model.vaga.VagaDeficiencia;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.model.vaga.VagaSuportePCD;
import com.pcdjob.repository.BeneficioRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.FormacaoDesejadaRepository;
import com.pcdjob.repository.HorarioRepository;
import com.pcdjob.repository.LocalTrabalhoRepository;
import com.pcdjob.repository.SalarioRepository;
import com.pcdjob.repository.SuporteRepository;
import com.pcdjob.repository.VagaBeneficioRepository;
import com.pcdjob.repository.VagaDeficienciaRepository;
import com.pcdjob.repository.VagaSuporteRepository;
import com.pcdjob.util.ConverterEnderecoEHorario;

public class InserirVagaDTO {
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
	
	public void salvarFormacaoDesejada(VagaEntity vaga, CursoRepository cursoRepository, FormacaoDesejadaRepository formacaoRepository) {
		List<Curso> cursos = converterCurso(cursoRepository);
		int indice = 0;
		while(cursos.size() > indice) {
			formacaoRepository.save(new FormacaoDesejada(vaga, cursos.get(indice)));
			indice++;
		}
	}
	
	private List<Curso> converterCurso(CursoRepository cursoRepository) {
		if(formacaoDesejada.size() > 0) {
			int indice = 0;
			List<Curso> cursos = new ArrayList<>();
			while(formacaoDesejada.size() > indice) {
				Optional<Curso> curso = cursoRepository.findById(formacaoDesejada.get(indice)); 
				cursos.add(curso.get());
				indice++;
			}
			return cursos;
		} else {
			List<Curso> cursos = new ArrayList<>();
			return cursos;
		}
	}
	
	public void salvarBeneficiosVaga(VagaEntity vaga, BeneficioRepository beneficioRepository, VagaBeneficioRepository vagaBeneficioRepository) {
		List<Beneficio> beneficios = converterBeneficio(beneficioRepository);
		int indice = 0;
		while(beneficios.size() > indice) {
			VagaBeneficio vagaBeneficio = new VagaBeneficio(vaga, beneficios.get(indice));
			vagaBeneficioRepository.save(vagaBeneficio);
			indice++;
		}
	}
	
	private List<Beneficio> converterBeneficio(BeneficioRepository beneficioRepository) {
		if(beneficio.size() > 0) {
			int indice = 0;
			List<Beneficio> beneficios = new ArrayList<>();
			while(beneficio.size() > indice) {
				Optional<Beneficio> beneficioObj = beneficioRepository.findById(beneficio.get(indice)); 
				if(beneficioObj.isPresent()) {
					beneficios.add(beneficioObj.get());
				}
				indice++;
			}
			return beneficios;
		} else {
			List<Beneficio> beneficios = new ArrayList<>();
			return beneficios;
		}
	}
	
	public void salvarDeficienciasVaga(VagaEntity vaga, DeficienciaRepository deficienciaRepository, VagaDeficienciaRepository vagaDeficienciaRepository) {
		List<Deficiencia> deficiencias = converterDeficiencia(deficienciaRepository);
		int indice = 0;
		while(deficiencias.size() > indice) {
			vagaDeficienciaRepository.save(new VagaDeficiencia(vaga, deficiencias.get(indice)));
			indice++;
		}
	}
	
	private List<Deficiencia> converterDeficiencia(DeficienciaRepository deficienciaRepository) {
		int indice = 0;
		List<Deficiencia> deficiencias = new ArrayList<>();
		while(deficiencias.size() > indice) {
			Optional<Deficiencia> deficienciaObj = deficienciaRepository.findById(deficiencia.get(indice));
			deficiencias.add(deficienciaObj.get());
			indice++;
		}
		return deficiencias;
	}
	
	public void salvarSuportesVaga(VagaEntity vaga, SuporteRepository suporteRepository, VagaSuporteRepository vagaSuporteRepository) {
		List<SuportePCD> suportes = converterSuporte(suporteRepository);
		
		int indice = 0;
		while(suportes.size() > indice) {
			vagaSuporteRepository.save(new VagaSuportePCD(vaga, suportes.get(indice)));
			indice++;
		}
	}
	
	private List<SuportePCD> converterSuporte(SuporteRepository suporteRepository) {
		if(suporte.size() > 0) {
			int indice = 0;
			List<SuportePCD> suportes = new ArrayList<>();
			while(suporte.size() > indice) {
				SuportePCD suporteObj = suporteRepository.getOne(suporte.get(indice));
				suportes.add(suporteObj);
				indice++;
			}
			return suportes;
		} else {
			List<SuportePCD> suportes = new ArrayList<>();
			return suportes;
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
	
	public VagaEntity converter(EmpresaEntity empresa, EstadoRepository estadoRepository, CidadeRepository cidadeRepository, TipoContrato contrato, 
			HorarioRepository horarioRepository, SalarioRepository salarioRepository, LocalTrabalhoRepository localTrabalhoRepository) {
		
		ConverterEnderecoEHorario conversor = new ConverterEnderecoEHorario();
		Cidade cidadeSalva = conversor.cidade(cidadeRepository, estadoRepository, cidade, sigla, estado);
		LocalTrabalho localTrabalho = localTrabalhoRepository.save(new LocalTrabalho(rua, numero, cep, bairro, cidadeSalva));
		Horario horarioSalvo = conversor.horario(horarioInicio, horarioSaida, statusHorario, horarioRepository);
		Salario salarioObj = converterSalario(salarioRepository);
		
		return new VagaEntity(status, titulo, descricao, requisitos, contrato, horarioSalvo, empresa, localTrabalho, salarioObj);
	}
}
