package com.pcdjob.controller.dto.response;

import java.util.List;

import com.pcdjob.controller.dto.AreaDTO;

public class ResponseEmpresaDTO {
	private Long id;
	private String nome;
	private String descricao;
	private List<ResponseEmailEmpresa> email;
	private List<ResponseTelefoneEmpresa> telefone;
	private AreaDTO areaAtuacao;
	private ResponseEnderecoEmpresa endereco;
	
	
}
