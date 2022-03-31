package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	Optional<Cidade> findByCidade(String cidade);

}
