package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.repository.AreaAtuacaoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class AreaAtuacaoService {
	
	@Autowired
	private AreaAtuacaoRepository areaRepository;
	
	public AreaAtuacao buscarAreaID(Long id) {
		Optional<AreaAtuacao> optional = areaRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Page<AreaAtuacao> paginarAreaAtuacao(Pageable paginacao) {
		return areaRepository.findAll(paginacao);
	}
}
