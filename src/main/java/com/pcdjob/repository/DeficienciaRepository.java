package com.pcdjob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pcdjob.model.Deficiencia;
import com.pcdjob.model.TipoDeficiencia;

public interface DeficienciaRepository extends JpaRepository<Deficiencia, Long> {

	Page<Deficiencia> findByTipoDeficiencia(TipoDeficiencia tipoDeficiencia, Pageable paginacao);

}
