package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Nivel;

@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {

	Nivel findByNivel(String nivel);

}
