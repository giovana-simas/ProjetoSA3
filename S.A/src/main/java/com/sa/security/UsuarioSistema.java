package com.sa.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//cria classe extenção de User(classe pre existente do security)
public class UsuarioSistema extends User{
	
	/**
	 * 
	 */
	// nao sei o que é isso
	private static final long serialVersionUID = 7157630742433339807L;
	//cria uma variavel nome
	private String nome;
	//cria um metodo com diverços atributos dentre eles sera decidido as autorizações atravez do "Collection<? extends GrantedAuthority> autorizacoes"
	public UsuarioSistema(String nome, String email, String senha, Collection<? extends GrantedAuthority> autorizacoes) {
		//envia para User os valores das variaveis
		super(email, senha, autorizacoes);
		this.nome = nome;
	}
	//cria a variavel que pegara o nome
	public String getNome() {
		return this.nome;
	}
}
