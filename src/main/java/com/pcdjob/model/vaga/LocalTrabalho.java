package com.pcdjob.model.vaga;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pcdjob.model.Cidade;

@Entity
@Table(name = "tbl_local_trabalho")
public class LocalTrabalho {
	@Id
	@Column(name = "id_local_trabalho")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String numero;
	private String cep;
	private String bairro;
	@ManyToOne
	private Cidade cidade;
	@OneToMany(mappedBy = "localTrabalho")
	private List<VagaEntity> vaga;
	
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
	public List<VagaEntity> getVaga() {
		return vaga;
	}
	public void setVaga(List<VagaEntity> vaga) {
		this.vaga = vaga;
	}
	
	public LocalTrabalho() {
		
	}
	
	public LocalTrabalho(String rua, String numero, String cep, String bairro, Cidade cidade) {
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.rua = rua;
		this.cidade = cidade;
	}
	
}
