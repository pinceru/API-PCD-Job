package com.pcdjob.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pcdjob.controller.dto.ImagemDTO;
import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.candidato.CandidatoFoto;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EmpresaFoto;
import com.pcdjob.service.CandidatoService;
import com.pcdjob.service.EmpresaService;
import com.pcdjob.service.FotoService;

@RestController
@RequestMapping("/file")
public class ImagemController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CandidatoService candidatoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@CrossOrigin
	@PostMapping(path = "/candidato/{id}", produces = "application/json")
	public ResponseEntity<?> postImageCandidato(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
		try {
			if(!file.isEmpty()) {
				CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
				byte[] bytes = Base64Utils.encode(file.getBytes());
				fotoService.salvarImagemCandidato(bytes, candidato);
				return ResponseEntity.ok().build();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping(path = "/candidato/{id}", produces = "application/json")
	public ResponseEntity<?> buscarImagemCandidato(@PathVariable Long id) {
		try {
			CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
			CandidatoFoto foto = fotoService.buscarFotoCandidatoID(candidato);
			return ResponseEntity.ok().body(new ImagemDTO(foto.getFoto()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@PostMapping(path = "/emrpesa/{id}", produces = "application/json")
	public ResponseEntity<?> postImageEmpresa(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
		try {
			if(!file.isEmpty()) {
				EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
				byte[] bytes = Base64Utils.encode(file.getBytes());
				fotoService.salvarImagemEmpresa(bytes, empresa);
				return ResponseEntity.ok().build();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping(path = "/empresa/{id}", produces = "application/json")
	public ResponseEntity<?> buscarImagemEmpresa(@PathVariable Long id) {
		try {
			EmpresaEntity empresa = empresaService.buscarEmpresaID(id);
			EmpresaFoto foto = fotoService.buscarFotoEmrpesaID(empresa);
			return ResponseEntity.ok().body(new ImagemDTO(foto.getFoto()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
