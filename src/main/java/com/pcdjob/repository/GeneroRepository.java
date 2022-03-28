package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
	Genero findByGenero(String genero);
}
