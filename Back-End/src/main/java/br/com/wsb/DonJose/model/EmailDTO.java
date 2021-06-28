package br.com.wsb.DonJose.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Email
	private String email;
	
	public EmailDTO(){
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}