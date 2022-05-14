package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	Optional<Cidade> findByCidade(String cidade);

	Page<Cidade> findByEstado(Estado estado, Pageable paginacao);
	

}
