package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.Salario;

@Repository
public interface SalarioRepository extends JpaRepository<Salario, Long>{
	Optional<Salario> findBySalarioAndVisivel(Float salario, int statuSalario);

}
