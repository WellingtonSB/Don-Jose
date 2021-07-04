package br.com.wsb.DonJose.service;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

import br.com.wsb.DonJose.model.Cliente;


public interface EmailService {
	
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendResetHtmlEmail(Cliente usuario, String newPass);
	
	void sendNewPasswordEmail(Cliente usuario, String newPass);
}
