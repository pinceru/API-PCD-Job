package com.pcdjob.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${support.mail}")
	private String emailSuporte;
	
	public void enviarEmail(String subject, String email, String conteudo) throws MessagingException {
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mail);
		message.setSubject(subject);
		message.setText(conteudo, false);
		message.setFrom(emailSuporte);
		message.setTo(email);
		
		mailSender.send(mail);
	}
	
	public String pegarConteudo() {
		return "";
	}
	
}
