package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.SuportePCD;
import com.pcdjob.model.vaga.VagaEntity;
import com.pcdjob.model.vaga.VagaSuportePCD;

@Repository
public interface VagaSuporteRepository extends JpaRepository<VagaSuportePCD, Long>{

	Optional<VagaSuportePCD> findBySuporteAndVaga(SuportePCD suportePCD, VagaEntity vaga);

	Optional<VagaSuportePCD> findBySuporte(SuportePCD suportePCD);

}
