package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.vaga.VagaDeficiencia;
import com.pcdjob.model.vaga.VagaEntity;

@Repository
public interface VagaDeficienciaRepository extends JpaRepository<VagaDeficiencia, Long>{

	Optional<VagaDeficiencia> findByDeficiencia(Deficiencia deficiencia);

	Optional<VagaDeficiencia> findByDeficienciaAndVaga(Deficiencia deficiencia, VagaEntity vaga);

}
