package com.pcdjob.service.helper;

public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public void mensagem() {
		System.out.println("Não foi possível encontrar o item com o id solicitado no banco de dados.");
	}
}
