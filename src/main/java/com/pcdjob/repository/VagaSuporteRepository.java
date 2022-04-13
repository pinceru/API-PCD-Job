package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.VagaSuportePCD;

@Repository
public interface VagaSuporteRepository extends JpaRepository<VagaSuportePCD, Long>{

}
