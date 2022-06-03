package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Curso;
import com.pcdjob.model.Nivel;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	Page<Curso> findByAreaAtuacaoAndNivel(AreaAtuacao area, Nivel nivelCurso, Pageable paginacao);

	Page<Curso> findByAreaAtuacao(AreaAtuacao area, Pageable paginacao);

	Page<Curso> findByNivel(Nivel nivel, Pageable paginacao);
	
	Optional<Curso> findByCurso(String palavra);

	Optional<Curso> findByCursoContaining(String palavra);

}
