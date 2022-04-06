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

@Entity
@Table(name = "tbl_vaga")
public class VagaEntity {
	@Id
	@Column(name = "id_vaga")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int status;
	private String titulo;
	private String descricao;
	private String requisitos;
	@ManyToOne
	private TipoContrato tipoContrato;
	@OneToMany(mappedBy = "vaga")
	private List<VagaSalario> vagaSalario;
}
