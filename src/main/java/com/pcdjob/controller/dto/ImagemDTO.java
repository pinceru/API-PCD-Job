package com.pcdjob.controller.dto;

public class ImagemDTO {
	private byte[] imagem;

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public ImagemDTO(byte[] imagem) {
		this.imagem = imagem;
	}
}
