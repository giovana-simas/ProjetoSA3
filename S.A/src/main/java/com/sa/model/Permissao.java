package com.sa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.sa.model.Usuario;

//cria um model/tabela onde as informações serão salvas e passadas para o banco de dados
@Entity(name = "permissao")
public class Permissao {
	
	//"@Id" se torna um id da tabela e primary key
	//"@GeneratedValue" define o tipo de estrategia de geração de ids
	//"@NonNull" torna o elemento não nulo impedindo que salve a informação vazia
	//"@Size" define o tamanho da variavel
	//"@ManyToMany" cria relação muitos para muitos entre duas tabelas
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NonNull
	@Size(max=80)
	private String nome;
	
	@ManyToMany(mappedBy = "permissoes")
	private List<Usuario> usuarios;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
