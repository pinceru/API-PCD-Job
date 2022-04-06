package com.pcdjob.controller.dto;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pcdjob.model.AreaAtuacao;
import com.pcdjob.model.Cidade;
import com.pcdjob.model.Estado;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EnderecoEmpresa;
import com.pcdjob.model.empresa.TelefoneEmpresa;
import com.pcdjob.repository.AreaAtuacaoRepository;
import com.pcdjob.repository.CidadeRepository;
import com.pcdjob.repository.EmailEmpresaRepository;
import com.pcdjob.repository.EnderecoEmpresaRepository;
import com.pcdjob.repository.EstadoRepository;
import com.pcdjob.repository.TelefoneEmpresaRepository;

public class InserirEmpresaDTO {
	@NotNull @NotEmpty @Length(min = 1, max = 100)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String senha;
	private List<String> email;
	private List<String> telefone;
	private String descricao;
	private Long areaAtuacao;
	private String rua;
	private String cep;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String sigla;
	
	
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
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(Long areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	public List<String> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
	public EmpresaEntity converter(AreaAtuacaoRepository areaRepository) {
		Optional<AreaAtuacao> area = areaRepository.findById(areaAtuacao);
		if(descricao != null && !descricao.equals(" ")) {
			return new EmpresaEntity(nome, senha, descricao, area.get());
		} else {
			return new EmpresaEntity(nome, senha, area.get());
		}
	}
	
	public void converterTelefone(EmpresaEntity empresa, TelefoneEmpresaRepository telefoneRepository) {
		System.out.println(telefone.get(0));
		int indice = 0;
		while(indice < telefone.size()) {
			Optional<TelefoneEmpresa> optional = telefoneRepository.findByNumero(telefone.get(indice));
			if(optional.isPresent() != true) {
				telefoneRepository.save(new TelefoneEmpresa(telefone.get(indice), empresa));
			}
			indice += 1;
		}
	}	
	
	public void converterEmail(EmpresaEntity empresa, EmailEmpresaRepository emailRepository) {
		int indice = 0;
		while(indice < email.size()) {
			Optional<EmailEmpresa> optional = emailRepository.findByEmail(email.get(indice));
			if(optional.isPresent() != true) {
				emailRepository.save(new EmailEmpresa(email.get(indice), empresa));
			}
			indice += 1;
		}
	}
	
	private Estado converterEstado(EstadoRepository estadoRepository) {
		Optional<Estado> estadoOptional = estadoRepository.findBySigla(sigla);
		if(estadoOptional.isPresent()) {
			return estadoOptional.get();
		} else {
			Estado novoEstado = new Estado(estado, sigla);
			return estadoRepository.save(novoEstado);
		}
	}
	
	private Cidade converterCidade(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findByCidade(cidade);
		if(cidadeOptional.isPresent()) {
			return cidadeOptional.get();
		} else {
			Estado estadoObj = converterEstado(estadoRepository);
			Cidade novaCidade = new Cidade(cidade, estadoObj);
			return cidadeRepository.save(novaCidade);
		}
	}
	
	public void converterEndereco(EmpresaEntity empresa, EnderecoEmpresaRepository enderecoRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
		Cidade cidadeSalva = converterCidade(cidadeRepository, estadoRepository);
		enderecoRepository.save(new EnderecoEmpresa(rua, numero, bairro, cep, cidadeSalva, empresa));
	}
}
