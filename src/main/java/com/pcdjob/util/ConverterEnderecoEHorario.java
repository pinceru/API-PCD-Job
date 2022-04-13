package com.pcdjob.util;

import java.util.Optional;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.model.vaga.Horario;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.HorarioRepository;

public class ConverterEnderecoEHorario {
	public Estado estado(EstadoRepository estadoRepository, String sigla, String estado) {
		Optional<Estado> estadoOptional = estadoRepository.findBySigla(sigla);
		if(estadoOptional.isPresent()) {
			return estadoOptional.get();
		} else {
			Estado novoEstado = new Estado(estado, sigla);
			return estadoRepository.save(novoEstado);
		}
	}
	
	public Cidade cidade(CidadeRepository cidadeRepository, EstadoRepository estadoRepository, String cidade, String sigla, String estado) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findByCidade(cidade);
		if(cidadeOptional.isPresent()) {
			return cidadeOptional.get();
		} else {
			Estado estadoObj = estado(estadoRepository, sigla, estado);
			Cidade novaCidade = new Cidade(cidade, estadoObj);
			return cidadeRepository.save(novaCidade);
		}
	}

	public Horario horario(String horarioInicio, String horarioSaida, int status, HorarioRepository horarioRepository) {
		Optional<Horario> horario = horarioRepository.findByHorarioInicioAndHorarioSaidaAndVisivel(horarioInicio, horarioSaida, status);
		if(horario.isPresent()) {
			return horario.get();
		} else {
			return horarioRepository.save(new Horario(horarioInicio, horarioSaida, status));
		}
	}
}
