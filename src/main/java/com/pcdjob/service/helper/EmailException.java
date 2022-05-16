package com.pcdjob.service.helper;

public class EmailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public void mensagem() {
		System.out.println("Email já cadastrado no banco de dados.");
	}
}
