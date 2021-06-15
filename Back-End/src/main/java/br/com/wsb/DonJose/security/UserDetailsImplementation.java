package br.com.wsb.DonJose.security;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.wsb.DonJose.model.Cliente;

public class UserDetailsImplementation implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

	private List<GrantedAuthority> authorities;

	public UserDetailsImplementation(Cliente cliente) {
		this.userName = cliente.getEmail();
		this.password = cliente.getSenha();

	}

	public UserDetailsImplementation() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}