package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long>{

	Optional<Horario> findByHorarioInicioAndHorarioSaida(String horarioInicio, String horarioSaida);

	Optional<Horario> findByHorarioInicioAndHorarioSaidaAndVisivel(String horarioInicio, String horarioSaida,
			int status);

}
