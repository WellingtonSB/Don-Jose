package br.com.wsb.DonJose.service;


import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import br.com.wsb.DonJose.model.Cliente;

public  class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}

	@Override
	public void sendResetHtmlEmail(Cliente usuario, String newPass) {
		// TODO Auto-generated method stub
		
	}
}
