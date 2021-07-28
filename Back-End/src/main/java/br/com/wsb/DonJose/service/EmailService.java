package br.com.wsb.DonJose.service;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

import br.com.wsb.DonJose.model.Cliente;
import br.com.wsb.DonJose.model.Pedido;


public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
		
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendNewPasswordEmail(Cliente usuario, String newPass);
}