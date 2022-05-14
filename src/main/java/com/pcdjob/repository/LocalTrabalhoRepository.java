package com.pcdjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.vaga.LocalTrabalho;

@Repository
public interface LocalTrabalhoRepository extends JpaRepository<LocalTrabalho, Long> {

	List<LocalTrabalho> findByCidade(Cidade cidade);

}
