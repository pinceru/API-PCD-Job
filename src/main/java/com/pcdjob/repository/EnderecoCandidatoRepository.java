package com.pcdjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.candidato.EnderecoCandidato;

@Repository
public interface EnderecoCandidatoRepository extends JpaRepository<EnderecoCandidato, Long>{

	List<EnderecoCandidato> findByCidade(Cidade cidade);

}
