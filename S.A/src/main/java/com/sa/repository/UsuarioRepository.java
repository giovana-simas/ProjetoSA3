package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

	public Usuario findByLogin(String login);
	public Usuario findByEmail(String email);
}
