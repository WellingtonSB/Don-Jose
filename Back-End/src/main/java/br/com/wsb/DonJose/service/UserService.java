package br.com.wsb.DonJose.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.wsb.DonJose.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}
}