package com.pcdjob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pcdjob.model.candidato.EmailCandidato;

@Entity
@Table(name = "tbl_recuperar_candidato")
public class RecuperarSenhaCandidato {
	@Id
	@Column(name = "id_recuperar_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer codigo;
	@ManyToOne
	private EmailCandidato email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public EmailCandidato getEmail() {
		return email;
	}
	public void setEmail(EmailCandidato email) {
		this.email = email;
	}
	
	public RecuperarSenhaCandidato(Integer codigo, EmailCandidato email) {
		this.email = email;
		this.codigo = codigo;
	}
}
