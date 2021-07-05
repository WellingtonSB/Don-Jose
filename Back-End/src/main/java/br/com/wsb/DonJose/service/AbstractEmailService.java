package br.com.wsb.DonJose.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.wsb.DonJose.model.Cliente;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	/*
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		 SimpleMailMessage sm = new SimpleMailMessage();
		 sm.setTo(obj.getCliente().getEmail());
		 sm.setFrom(sender);
		 sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		 sm.setSentDate(new Date(System.currentTimeMillis()));
		 sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
			
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}
	
	*/
	
	@Override
	public void sendNewPasswordEmail(Cliente usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente usuario, String newPass) {
		 SimpleMailMessage sm = new SimpleMailMessage();
		 sm.setTo(usuario.getUsuario());
		 sm.setFrom(sender);
		 sm.setSubject("Solicitação de nova senha");
		 sm.setSentDate(new Date(System.currentTimeMillis()));
		 sm.setText("Nova senha: " + newPass);
		return sm;
	}
	
	
	protected String htmlFromTemplateUsuario(Cliente obj, String newPass) {
		Context context = new Context();
		context.setVariable("usuario", obj);
		context.setVariable("password", newPass);
		return templateEngine.process("email/resetDeSenha", context);
	}
	
	
	@Override
	public void sendResetHtmlEmail(Cliente obj, String newPass) {
		try {
			MimeMessage mm = prepareMimeMessageFromUsuario(obj, newPass);
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendNewPasswordEmail(obj, newPass);
			
		}
	}
	
	protected MimeMessage prepareMimeMessageFromUsuario(Cliente obj, String newPass) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Você solicitou uma nova senha de acesso");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateUsuario(obj, newPass),true);
		return mimeMessage;
	}

}

//https://receitasdecodigo.com.br/spring-boot/como-configurar-projetos-spring-boot-para-enviar-e-mail#:~:text=Caso%20conecte%20no%20Gmail%20precisa,menos%20seguros%20em%20sua%20conta.&text=Agora%20basta%20rodar%20a%20aplicação,mail%20em%20texto%20puro%20apenas.
