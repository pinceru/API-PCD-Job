package com.pcdjob.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcdjob.controller.dto.VagaSalvaDTO;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.VagaRepository;

//@RestController 
//@RequestMapping("/pesquisa")
//public class PesquisaController {
//
//	@Autowired 
//	private EmpresaRepository empresaRepository;
//	
//	@Autowired 
//	private VagaRepository vagaRepository;
//	
////	@CrossOrigin
////	@GetMapping(path = "/", produces = "application/json")
////	public Page<?> pesquisarCandidato(@RequestParam(required = true) String palavra, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
////
////		
////	}
//}
