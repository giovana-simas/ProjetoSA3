package com.sa.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.NonNull;



@Entity(name="usuario")
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames="email", name="uniqueEmailConstraint")
})
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("U")
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

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", columnDefinition = "varchar(32) default 'feminino'")
	Sexo sexo = Sexo.feminino;
	
	@NonNull
	@Size(max=255)
	String email;
	
	@NonNull
	@Size(max=255)
	String senha;

	@Size(max=500)
	String descricao;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="usuario_permissao",
			joinColumns=@JoinColumn(name="usuario_id"),
			inverseJoinColumns=@JoinColumn(name="permissao_id")
			)
	private Set<Permissao> permissoes;

	@OneToMany(mappedBy = "usuario1")
	public List<UsuarioChat> usuarioChats1;

	@OneToMany(mappedBy = "usuario2")
	public List<UsuarioChat> usuarioChats2;

	public List<UsuarioChat> getUsuarioChats1() {
		return usuarioChats1;
	}

	public enum Sexo {
		masculino("Masculino"), feminino("feminino");

		public final String displayValue;

		private Sexo(String displayValue) {
			this.displayValue = displayValue;
		}

		public String getDisplayValue() {
			return displayValue;
		}
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setUsuarioChats1(List<UsuarioChat> usuarioChats1) {
		this.usuarioChats1 = usuarioChats1;
	}

	public List<UsuarioChat> getUsuarioChats2() {
		return usuarioChats2;
	}

	public void setUsuarioChats2(List<UsuarioChat> usuarioChats2) {
		this.usuarioChats2 = usuarioChats2;
	}

	public void addUsuarioChats1(UsuarioChat usuarioChats) {
		this.usuarioChats1.add(usuarioChats) ;
	}

	public void addUsuarioChats2(UsuarioChat usuarioChats) {
		this.usuarioChats2.add(usuarioChats) ;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", permissoes=" + permissoes + "]";
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void addPermissao(Permissao permissao){
		this.permissoes.add(permissao);
	}
	
	
}
