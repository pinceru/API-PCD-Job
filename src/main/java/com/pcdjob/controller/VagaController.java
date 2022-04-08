package com.pcdjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.InserirVagaDTO;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.VagaRepository;

@RestController
@RequestMapping("/vaga")
public class VagaController {

	@Autowired
	private VagaRepository vagaRepository;
	
	public ResponseEntity<VagaDTO> cadastrarVaga(@PathVariable Long id, @RequestBody InserirVagaDTO insercaoDTO) {
		VagaEntity vaga = invercaoDTO.converter();
	}
}
