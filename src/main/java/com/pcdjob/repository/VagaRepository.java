package com.pcdjob.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.candidato.CandidatoEntity;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.vaga.TipoContrato;
import com.pcdjob.model.vaga.VagaEntity;

@Repository
public interface VagaRepository extends JpaRepository<VagaEntity, Long>{
	List<VagaEntity> findAllByEmpresa(EmpresaEntity empresaEntity);

	List<VagaEntity> findByTitulo(String palavra);

	Optional<VagaEntity> findByTipoContrato(TipoContrato vagaContrato);

	List<VagaEntity> findByVagaCandidatoCandidatoNot(CandidatoEntity candidato);

	
}
