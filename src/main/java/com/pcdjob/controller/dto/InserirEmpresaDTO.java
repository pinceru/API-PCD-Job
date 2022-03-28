package com.pcdjob.controller.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.repository.EmpresaRepository;

public class InserirEmpresaDTO {
	@NotNull @NotEmpty @Length(min = 1, max = 100)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String senha;
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public EmpresaEntity converter() {
		return new EmpresaEntity(nome, senha);
	}
	
	public EmailEmpresa converterEmail(EmpresaRepository empresaRepository, EmpresaEntity empresa) {
		Long idEmpresa = empresa.getId();
		Optional<EmpresaEntity> empresaObj = empresaRepository.findById(idEmpresa);
		return new EmailEmpresa(email, empresaObj.get());
	}
}
