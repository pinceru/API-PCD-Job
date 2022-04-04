package com.pcdjob.model.candidato;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_candidato")
public class CandidatoEntity {
	
	@Id
	@Column(name = "id_candidato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String senha;
	private String fotoPerfil;
	private String banner;
	private String dataNascimento;
	private String nomeSocial;
	private String informacoes;
	
	@JoinColumn(name = "id_genero")
	@ManyToOne
	private Genero genero;

	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
	private List<DeficienciaCandidato> deficienciaCandidato = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
	private List<TelefoneCandidato> telefoneCandidato = new ArrayList<>(); 
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
	private List<EmailCandidato> emailCandidato = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
	private List<ExperienciaProfissional> experienciaProfissional = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
	private List<CursoCandidato> cursoCandidato = new ArrayList<>();
	@OneToOne(mappedBy = "candidato", cascade = CascadeType.ALL)
	private EnderecoCandidato endereco;
	
	public CandidatoEntity() {
		
	}
	
	public CandidatoEntity(String nome, String senha, Genero genero) {
		this.nome = nome;
		this.senha = senha;
		this.genero = genero;
	}
	
	public List<EmailCandidato> getEmailCandidato() {
		return emailCandidato;
	}
	public void setEmailCandidato(List<EmailCandidato> emailCandidato) {
		this.emailCandidato = emailCandidato;
	}
	public List<DeficienciaCandidato> getDeficienciaCandidato() {
		return deficienciaCandidato;
	}
	public void setDeficienciaCandidato(List<DeficienciaCandidato> deficienciaCandidato) {
		this.deficienciaCandidato = deficienciaCandidato;
	}
	public List<TelefoneCandidato> getTelefoneCandidato() {
		return telefoneCandidato;
	}
	public void setTelefoneCandidato(List<TelefoneCandidato> telefoneCandidato) {
		this.telefoneCandidato = telefoneCandidato;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getDataNascimento() {
		return dataNascimento;
		}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNomeSocial() {
		return nomeSocial;
	}
	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public List<ExperienciaProfissional> getExperienciaProfissional() {
		return experienciaProfissional;
	}

	public void setExperienciaProfissional(List<ExperienciaProfissional> experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}

	public List<CursoCandidato> getCursoCandidato() {
		return cursoCandidato;
	}

	public void setCursoCandidato(List<CursoCandidato> cursoCandidato) {
		this.cursoCandidato = cursoCandidato;
	}

	public EnderecoCandidato getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoCandidato endereco) {
		this.endereco = endereco;
	}

	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	
}
