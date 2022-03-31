package com.pcdjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcdjob.model.empresa.TelefoneEmpresa;

@Repository
public interface TelefoneEmpresaRepository extends JpaRepository<TelefoneEmpresa, Long> {

	Optional<TelefoneEmpresa> findByNumero(String string);

}
