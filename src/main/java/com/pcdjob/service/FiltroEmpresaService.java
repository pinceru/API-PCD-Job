package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.Estado;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.EnderecoCandidato;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.DeficienciaRepository;
import com.pcdjob.repository.EnderecoCandidatoRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class FiltroEmpresaService {
	
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoCandidatoRepository enderecoCandidatoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<CandidatoEntity> filtrarPorDeficiencia(Long idDeficiencia) {
		Optional<Deficiencia> deficiencia = deficienciaRepository.findById(idDeficiencia);
		List<CandidatoEntity> candidatos = new ArrayList<>();
		for(int i = 0; i < deficiencia.get().getDeficienciaCandidato().size(); i++) {
			CandidatoEntity candidato = deficiencia.get().getDeficienciaCandidato().get(i).getCandidato(); 
			candidatos.add(candidato);
		}	
		return candidatos;
	}
	
	public List<CandidatoEntity> filtrarPorCidade(Long idCidade) {
		int indice = 0;
		List<CandidatoEntity> candidatos = new ArrayList<>();
		
		Optional<Cidade> cidade = cidadeRepository.findById(idCidade);
		if(Verificar.verificarOptional(cidade)) {
			List<EnderecoCandidato> endereco = enderecoCandidatoRepository.findByCidade(cidade.get());
			if(endereco.size() > 0) {
				return listarCandidatosPorEndereco(indice, endereco);
			} else {
				return candidatos;
			}
		} else {
			return candidatos;
		}
	}
	
	public List<CandidatoEntity> filtrarPorEstado(Long idEstado) {
		int indiceCidade = 0;
		List<CandidatoEntity> candidatos = new ArrayList<>();
		
		Optional<Estado> estado = estadoRepository.findById(idEstado);
		List<Cidade> cidades =  estado.get().getCidade();
		if(cidades.size() > 0) {
			while(indiceCidade < cidades.size()) {
				List<EnderecoCandidato> locais = enderecoCandidatoRepository.findByCidade(cidades.get(indiceCidade));
				if(locais.size() > 0) {
					candidatos.addAll(listarCandidatosPorEndereco(0, locais));
				}
				indiceCidade++;
			}
		}
		return candidatos;
	}

	private List<CandidatoEntity> listarCandidatosPorEndereco(int indice, List<EnderecoCandidato> endereco) {
		List<CandidatoEntity> candidatos = new ArrayList<>();
		if(endereco.size() > 0) {
			while(indice < endereco.size()) {
				CandidatoEntity candidato = endereco.get(indice).getCandidato();
				candidatos.add(candidato);
				indice++;
			}
		}
		return candidatos;
	}
	
	
}
