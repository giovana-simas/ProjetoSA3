package com.sa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.NonNull;



@Entity(name="usuario")
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames="email", name="uniqueEmailConstraint")
})
public class Usuario {
		//"@Id" se torna um id da tabela e primary key
		//"@GeneratedValue" define o tipo de estrategia de geração de ids
		//"@NonNull" torna o elemento não nulo impedindo que salve a informação vazia
		//"@Size" define o tamanho da variavel
		//"@ManyToMany" cria relação muitos para muitos entre duas tabelas
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@NonNull
	@Size(max=255)
	String nome;
	
	@NonNull
	@Size(max=255)
	String email;
	
	@NonNull
	@Size(max = 80)
	String login;
	
	@NonNull
	@Size(max=255)
	String senha;
	
	@ManyToMany
	@JoinTable(
			name="usuario_permissao",
			joinColumns=@JoinColumn(name="usuario_id"),
			inverseJoinColumns=@JoinColumn(name="permissao_id")
			)
	private List<Permissao> permissoes;
	
	

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ", permissoes=" + permissoes + "]";
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
