package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.Beneficio;
import com.pcdjob.model.vaga.VagaBeneficio;
import com.pcdjob.model.vaga.VagaEntity;

@Repository
public interface VagaBeneficioRepository extends JpaRepository<VagaBeneficio, Long> {

	Optional<VagaBeneficio> findByBeneficioAndVaga(Beneficio beneficio, VagaEntity vaga);

	Optional<VagaBeneficio> findByBeneficio(Beneficio beneficio);


}
