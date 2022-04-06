package com.pcdjob.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.TelefoneEmpresa;
import com.pcdjob.repository.AreaAtuacaoRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.EmpresaRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.TelefoneEmpresaRepository;

public class AtualizarEmpresaDTO {
	private String nome;
	private String descricao;
	private List<String> email;
	private List<String> telefone;
	private Long areaAtuacao;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String sigla;
	private String cep;
	private String numero;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}
	public Long getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(Long areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public EmpresaEntity converter(Long id, EmpresaRepository empresaRepository, AreaAtuacaoRepository areaRepository, TelefoneEmpresaRepository telefoneRepository, EmailEmpresaRepository emailRepository,
																EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
		Optional<AreaAtuacao> area = areaRepository.findById(areaAtuacao);
		Optional<EmpresaEntity> optional = empresaRepository.findById(id);
		Optional<Estado> estadoOptional = estadoRepository.findBySigla(sigla);
		Optional<Cidade> cidadeOptional = cidadeRepository.findByCidade(cidade);
		EmpresaEntity empresa = optional.get();
		empresa.setAreaAtuacao(area.get());
		empresa.setDescricao(descricao);
		empresa.setNome(nome);
		empresa.setTelefoneEmpresa(converterTelefone(empresa, telefoneRepository));
		empresa.setEmailEmpresa(converterEmail(empresa, emailRepository));
		if(cidadeOptional.isPresent()) {
			empresa.getEndereco().setCidade(cidadeOptional.get());
		} else if(estadoOptional.isPresent() && cidadeOptional.isPresent() != true) {
			Cidade novaCidade = cidadeRepository.save(new Cidade(cidade, estadoOptional.get()));
			empresa.getEndereco().setCidade(novaCidade);
		} else {
			Estado novoEstado = estadoRepository.save(new Estado(estado, sigla));
			Cidade novaCidade = cidadeRepository.save(new Cidade(cidade, novoEstado));
			empresa.getEndereco().setCidade(novaCidade);
		}
		empresa.getEndereco().setBairro(bairro);
		empresa.getEndereco().setCep(cep);
		empresa.getEndereco().setNumero(numero);
		empresa.getEndereco().setRua(rua);
		return empresa;
	}
	
	private List<TelefoneEmpresa> converterTelefone(EmpresaEntity empresa, TelefoneEmpresaRepository telefoneRepository) {
		List<TelefoneEmpresa> listaTelefone = new ArrayList<>();
		int indice = 1;
		while(telefone.size() < indice) {
			Optional<TelefoneEmpresa> telefoneOptional = telefoneRepository.findByEmpresaAndNumero(empresa, telefone.get(indice));
			if(telefoneOptional.isPresent()) {
				listaTelefone.add(telefoneOptional.get());
			} else {
				TelefoneEmpresa telefoneObj = new TelefoneEmpresa(telefone.get(indice), empresa);
				listaTelefone.add(telefoneRepository.save(telefoneObj));
			}
			indice++;
		}
		return listaTelefone;
	}
	
	private List<EmailEmpresa> converterEmail(EmpresaEntity empresa, EmailEmpresaRepository emailRepository) {
		List<EmailEmpresa> listaEmail = new ArrayList<>();
		int indice = 1;
		while(email.size() < indice) {
			Optional<EmailEmpresa> optional = emailRepository.findByEmpresaAndEmail(empresa, email.get(indice));
			if(optional.isPresent()) {
				listaEmail.add(optional.get());
			} else {
				EmailEmpresa emailObj = new EmailEmpresa(email.get(indice), empresa);
				listaEmail.add(emailRepository.save(emailObj));
			}
			indice++;
		}
		return listaEmail;
	}
	
	
}
