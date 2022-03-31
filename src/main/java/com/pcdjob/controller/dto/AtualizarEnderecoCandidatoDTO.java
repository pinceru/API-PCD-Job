package com.pcdjob.controller.dto;

import java.util.Optional;

import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.model.candidato.EnderecoCandidato;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.EnderecoCandidatoRepository;
import com.pcdjob.repository.EstadoRepository;

public class AtualizarEnderecoCandidatoDTO {
	private String rua;
	private String cep;
	private String cidade;
	private String bairro;
	private String numero;
	private String estado;
	private String sigla;

	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public EnderecoCandidato converter(Long id, EnderecoCandidatoRepository enderecoRepository, EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
		Optional<EnderecoCandidato> optional = enderecoRepository.findById(id);
		EnderecoCandidato endereco = optional.get();
		Optional<Estado> estadoObj = estadoRepository.findBySigla(sigla);
		Optional<Cidade> cidadeObj = cidadeRepository.findByCidade(cidade);
		if(cidadeObj.isPresent()) {
			endereco.setCidade(cidadeObj.get());
		} else if(estadoObj.isPresent() && cidadeObj.isPresent() != true) {
			Cidade novaCidade = new Cidade(cidade, estadoObj.get());	
			Cidade cidadeSalva = cidadeRepository.save(novaCidade);
			endereco.setCidade(cidadeSalva);
		} else {
			Estado novoEstado = new Estado(estado, sigla);
			Estado estadoSalvo = estadoRepository.save(novoEstado);
			Cidade novaCidade = new Cidade(cidade, estadoSalvo);	
			Cidade cidadeSalva = cidadeRepository.save(novaCidade);
			endereco.setCidade(cidadeSalva);
		}
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setNumero(numero);
		endereco.setRua(rua);
		return endereco;
	}
}
