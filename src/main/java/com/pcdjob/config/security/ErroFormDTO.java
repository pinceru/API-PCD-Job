package com.pcdjob.config.security;

public class ErroFormDTO {
	private String nomeCampo;
	private String erro;
	
	public ErroFormDTO(String nomeCampo, String erro) {
		this.nomeCampo = nomeCampo;
		this.erro = erro;
	}
	
	public String getNomeCampo() {
		return nomeCampo;
	}
	
	public String getErro() {
		return erro;
	}
}
