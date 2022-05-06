package com.pcdjob.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.repository.TipoContratoRepository;
import com.pcdjob.service.helper.Verificar;

@Service
public class ContratoService {
	@Autowired
	private TipoContratoRepository contratoRepository;
	
	public TipoContrato buscarTipoContratoID(Long id) {
		Optional<TipoContrato> optional = contratoRepository.findById(id);
		if(Verificar.verificarOptional(optional)) {
			return optional.get();
		} else {
			return null;
		}
	}
}
