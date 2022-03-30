package com.pcdjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.AreaAtuacao;

@Repository
public interface AreaAtuacaoRepository extends JpaRepository<AreaAtuacao, Long>{

	AreaAtuacao findByAreaAtuacao(String areaAtuacao);

}
