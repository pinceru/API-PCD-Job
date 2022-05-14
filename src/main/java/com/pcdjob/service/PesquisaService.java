package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.Estado;
import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.TipoDeficiencia;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.FormacaoDesejada;
import com.pcdjob.model.vaga.LocalTrabalho;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.LocalTrabalhoRepository;
import com.pcdjob.repository.SuporteRepository;
import com.pcdjob.repository.TipoContratoRepository;
import com.pcdjob.repository.TipoDeficienciaRepository;
import com.pcdjob.repository.VagaRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class PesquisaService {
	
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TipoContratoRepository contratoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private TipoDeficienciaRepository tipoRepository;
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@Autowired
	private SuporteRepository suporteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private LocalTrabalhoRepository localRepository;
	
	public List<VagaEntity> filtraPesquisa(String palavra) {
		List<VagaEntity> vagas = new ArrayList<>();
		
		Optional<Curso> curso = cursoRepository.findByCurso(palavra);
		Optional<TipoContrato> tipoContrato = contratoRepository.findByTipoContrato(palavra);
		List<VagaEntity> vagasTitulo = vagaRepository.findByTitulo(palavra);
		Optional<EmpresaEntity> empresa = empresaRepository.findByNome(palavra);
		
		if(Verificar.verificarOptional(curso)) {
			List<FormacaoDesejada> formacao = curso.get().getFormacaoDesejada();
			if(formacao.size() > 0) {
				int indice = 0;
				while(indice < formacao.size()) {
					VagaEntity vaga = formacao.get(indice).getVaga();
					vagas.add(vaga);
					indice++;
				}
			}
		} else if(Verificar.verificarOptional(tipoContrato)) {
			vagas = tipoContrato.get().getVaga();
		} else if(vagasTitulo.size() > 0) {
			vagas = vagasTitulo;
		} else if(Verificar.verificarOptional(empresa)) {
			if(empresa.get().getVaga().size() > 0) {
				vagas = empresa.get().getVaga();
			}
		} 
		return vagas;
	}
	
	public List<VagaEntity> filtrarPorDeficiencia(Long idTipoDeficiencia) {
		int indiceDeficiencia = 0;
		int indiceVaga = 0;
		List<VagaEntity> retornoVagas = new ArrayList<>();
		
		if(idTipoDeficiencia != null) {
			TipoDeficiencia tipo = tipoRepository.getOne(idTipoDeficiencia);
			List<Deficiencia> deficiencias = deficienciaRepository.findByTipoDeficienciaTipo(tipo.getTipo());
			while(deficiencias.size() > indiceDeficiencia) {
				indiceVaga = 0;
				while(deficiencias.get(indiceDeficiencia).getVagaDeficiencia().size() > indiceVaga) {
					VagaEntity vaga = deficiencias.get(indiceDeficiencia).getVagaDeficiencia().get(indiceVaga).getVaga();
					retornoVagas.add(vaga);
					indiceVaga++;
				}
				indiceDeficiencia++;
			}
		}
		return retornoVagas;
	}
	
	public List<VagaEntity> filtrarPorSuporte(Long idSuporte) {
		int indice = 0;
		List<VagaEntity> retornoVagas = new ArrayList<>();
		if(idSuporte != null) {
			SuportePCD suporte = suporteRepository.getOne(idSuporte);
			while(indice < suporte.getVagaSuporte().size()) {
				VagaEntity vaga = suporte.getVagaSuporte().get(indice).getVaga();
				retornoVagas.add(vaga);
				indice++;
			}
		}
		
		return retornoVagas;
	}
	
	public List<VagaEntity> filtrarPorCidade(Long idCidade) {
		int indice = 0;
		List<VagaEntity> retornoVagas = new ArrayList<>();
		
		Optional<Cidade> cidade = cidadeRepository.findById(idCidade);
		if(Verificar.verificarOptional(cidade)) {
			List<LocalTrabalho> locais = localRepository.findByCidade(cidade.get());
			if(locais.size() > 0) {
				return listarVagasPorLocalTrabalho(indice, locais);
			} else {
				return retornoVagas;
			}
		} else {
			return retornoVagas;
		}
	}
	
	public List<VagaEntity> filtrarPorEstado(Long idEstado) {
		int indiceCidade = 0;
		List<VagaEntity> retornoVagas = new ArrayList<>();
		
		Optional<Estado> estado = estadoRepository.findById(idEstado);
		List<Cidade> cidades =  estado.get().getCidade();
		while(indiceCidade < cidades.size()) {
			List<LocalTrabalho> locais = localRepository.findByCidade(cidades.get(indiceCidade));
			if(locais.size() > 0) {
				retornoVagas.addAll(listarVagasPorLocalTrabalho(0, locais));
			}
			indiceCidade++;
		}
		return retornoVagas;
	}
	
	private List<VagaEntity> listarVagasPorLocalTrabalho(int indice, List<LocalTrabalho> locais) {
		System.out.println("A cidade do local de trabalho é exatamente: " + locais.get(indice).getCidade().getCidade());
		List<VagaEntity> vagas = new ArrayList<>();
		while(indice < locais.size()) {
			VagaEntity vaga = locais.get(indice).getVaga().get(indice);
			vagas.add(vaga);
			indice++;
		}
		return vagas;
	}
}
