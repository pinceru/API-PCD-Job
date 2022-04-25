package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.vaga.StatusVaga;

@Repository
public interface StatusRepository extends JpaRepository<StatusVaga, Long> {

}
