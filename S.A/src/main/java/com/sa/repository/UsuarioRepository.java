package com.sa.repository;

import com.sa.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	//procura um usuario no banco pelo seu email
	public Usuario findByEmail(String email);
	public Usuario deleteByEmail(String email);
	public Usuario findById(long id);

}
