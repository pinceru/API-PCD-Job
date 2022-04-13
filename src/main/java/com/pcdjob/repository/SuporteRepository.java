package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.SuportePCD;

@Repository
public interface SuporteRepository extends JpaRepository<SuportePCD, Long>{

}
