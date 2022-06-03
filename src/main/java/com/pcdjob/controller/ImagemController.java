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
import com.pcdjob.service.CandidatoService;
import com.pcdjob.service.FotoService;

@RestController
@RequestMapping("/file")
public class ImagemController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CandidatoService candidatoService;
	
	
	@CrossOrigin
	@PostMapping(path = "/candidato/{id}", produces = "application/json")
	public ResponseEntity<?> postImage(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
		try {
			if(!file.isEmpty()) {
				CandidatoEntity candidato = candidatoService.buscarCandidatoID(id);
				byte[] bytes = file.getBytes();
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
	public ResponseEntity<?> buscarImagem(@PathVariable Long id) {
		try {
			CandidatoFoto foto = fotoService.buscarFotoCandidatoID(id);
			byte[] encoded = Base64Utils.encode(foto.getFoto());
			return ResponseEntity.ok().body(new ImagemDTO(encoded));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
