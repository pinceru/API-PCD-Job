package com.pcdjob.model.candidato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcdjob.model.Cidade;

@Entity
@Table(name = "tbl_endereco_candidato")
public class EnderecoCandidato {
	@Id
	@Column(name = "id_endereco_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String numero;
	private String cep;
	private String bairro;
	@ManyToOne
	private Cidade cidade;
	@OneToOne
	private CandidatoEntity candidato;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public CandidatoEntity getCandidato() {
		return candidato;
	}
	public void setCandidato(CandidatoEntity candidato) {
		this.candidato = candidato;
	}
	
	public EnderecoCandidato() {
		
	}
	
	public EnderecoCandidato(String rua, String numero, String cep, String bairro, Cidade cidade, CandidatoEntity candidato) {
		this.bairro = bairro;
		this.candidato = candidato;
		this.cep = cep;
		this.cidade = cidade;
		this.numero = numero;
		this.rua = rua;
	}
	
}
