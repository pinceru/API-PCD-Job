package com.pcdjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pcdjob.model.Curso;
import com.pcdjob.model.vaga.FormacaoDesejada;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.CursoRepository;
import com.pcdjob.repository.FormacaoDesejadaRepository;
import com.pcdjob.repository.TipoContratoRepository;
import com.pcdjob.repository.VagaRepository;

//@Service
//public class PesquisaService {
//	public List<VagaEntity> filtraPesquisa(VagaRepository vagaRepository, CursoRepository cursoRepository, FormacaoDesejadaRepository formacaoRepository, TipoContratoRepository contratoRepository, String palavra) {
//		Optional<FormacaoDesejada> formacao;
//		Optional<VagaEntity> vagaContrato;
//		List<VagaEntity> vagaTitulo = vagaRepository.findByTitulo(palavra);
//		List<VagaEntity> vagas = vagaRepository.findAll();
//		Optional<Curso> curso = cursoRepository.findByCurso(palavra);
//		Optional<TipoContrato> contrato = contratoRepository.findByTipoContrato(palavra);
//		if(curso.isPresent()) {
//			formacao = formacaoRepository.findByCurso(curso.get());
//		}
//		
//		int indice = 0;
//		while(indice < vagas.size()) {
//			if(contrato.isPresent()) {
//				vagaContrato = vagaRepository.findByTipoContrato(contrato.get());
//			}
//			
//		}
//	}
//}
