package com.pcdjob.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.pcdjob.controller.dto.response.ResponseEmailEmpresa;
import com.pcdjob.controller.dto.response.ResponseEnderecoEmpresa;
import com.pcdjob.controller.dto.response.ResponseTelefoneEmpresa;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EnderecoEmpresa;
import com.pcdjob.model.empresa.TelefoneEmpresa;

public class EmpresaSalvaDTO {
	private Long id;
	private String nome;
	private String descricao;
	private List<ResponseEmailEmpresa> email;
	private List<ResponseTelefoneEmpresa> telefone;
	private AreaDTO areaAtuacao;
	private ResponseEnderecoEmpresa endereco;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ResponseEmailEmpresa> getEmail() {
		return email;
	}

	public void setEmail(List<ResponseEmailEmpresa> email) {
		this.email = email;
	}

	public List<ResponseTelefoneEmpresa> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<ResponseTelefoneEmpresa> telefone) {
		this.telefone = telefone;
	}

	public AreaDTO getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaDTO areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public ResponseEnderecoEmpresa getEndereco() {
		return endereco;
	}

	public void setEndereco(ResponseEnderecoEmpresa endereco) {
		this.endereco = endereco;
	}

	public EmpresaSalvaDTO(EmpresaEntity empresa) {
		this.id = empresa.getId();
		this.nome = empresa.getNome();
		if(empresa.getDescricao() != " ") {
			this.descricao = empresa.getDescricao();
		}
		
		if(empresa.getEmailEmpresa().size() > 0) {
			int indice = 0;
			List<ResponseEmailEmpresa> emailEmpresaList = new ArrayList<>();
			while(indice < empresa.getEmailEmpresa().size() && indice < 2) {
				EmailEmpresa emailEmpresa = empresa.getEmailEmpresa().get(indice);
				ResponseEmailEmpresa response = new ResponseEmailEmpresa(emailEmpresa.getId(), emailEmpresa.getEmail());
				emailEmpresaList.add(response);
				indice++;
			}
			this.email = emailEmpresaList;
		}
		if(empresa.getTelefoneEmpresa().size() > 0) {
			int indice = 0;
			List<ResponseTelefoneEmpresa> telefoneEmpresaList = new ArrayList<>();
			while(indice < empresa.getTelefoneEmpresa().size() && indice < 2) {
				TelefoneEmpresa telefoneEmpresa = empresa.getTelefoneEmpresa().get(indice);
				ResponseTelefoneEmpresa response = new ResponseTelefoneEmpresa(telefoneEmpresa.getId(), telefoneEmpresa.getNumero());
				telefoneEmpresaList.add(response);
				indice++;
			}
			this.telefone = telefoneEmpresaList;
		}
		if(empresa.getAreaAtuacao() != null) {
			this.areaAtuacao = new AreaDTO(empresa.getAreaAtuacao());
		}
		if(empresa.getEndereco() != null) {
			EnderecoEmpresa e = empresa.getEndereco();
			this.endereco = 
					new ResponseEnderecoEmpresa(e.getId(), e.getRua(), e.getNumero(), e.getCep(), e.getBairro(), 
							e.getCidade().getId(), e.getCidade().getCidade(), e.getCidade().getEstado().getId(), e.getCidade().getEstado().getEstado(), e.getCidade().getEstado().getSigla());
		}
		
	}
	
	public static Page<EmpresaSalvaDTO> converter(Page<EmpresaEntity> empresas) {
		return empresas.map(EmpresaSalvaDTO::new);
	}
}
