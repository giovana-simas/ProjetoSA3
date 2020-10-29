package com.sa.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSistema extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7157630742433339807L;
	private String nome;
	
	public UsuarioSistema(String nome, String email, String senha,
			Collection<? extends GrantedAuthority> autorizacoes) {
		super(email, senha, autorizacoes);
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
