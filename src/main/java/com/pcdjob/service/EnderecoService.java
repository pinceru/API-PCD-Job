package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class EnderecoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Estado converterEstado(String sigla, String estado) {
		Optional<Estado> optional = estadoRepository.findBySigla(sigla);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			Estado novoEstado = new Estado(estado, sigla);
			return estadoRepository.save(novoEstado);
		}
	}
	
	public Cidade converterCidade(String cidade, String sigla, String estado) {
		Optional<Cidade> optional = cidadeRepository.findByCidade(cidade);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			Estado estadoObj = converterEstado(sigla, estado);
			Cidade novaCidade = new Cidade(cidade, estadoObj);
			return cidadeRepository.save(novaCidade);
		}
	}
}
