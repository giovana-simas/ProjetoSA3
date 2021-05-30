package com.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Permissao;

import com.sa.model.Usuario;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	// nao lembro exatamente o que isso faz mas se nao me engano encontra varios usuarios e os coloca em uma lista pelas suas permissoes.
	public List<Permissao> findByUsuariosIn(Usuario ... usuario);
	
	public Permissao findByNome(String nome);

}
