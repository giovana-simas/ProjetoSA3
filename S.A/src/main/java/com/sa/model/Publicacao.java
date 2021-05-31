package com.sa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;



@Entity
public class Publicacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NonNull
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", columnDefinition = "varchar(32) default 'ajuda'")
	public Tipo tipo = Tipo.ajuda;
	public enum Tipo {
		ajuda("Ajuda"), aviso("Aviso");

		public final String displayValue;

		private Tipo(String displayValue) {
			this.displayValue = displayValue;
		}

		public String getDisplayValue() {
			return displayValue;
		}
	}
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	public Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name ="instituicao_id", referencedColumnName = "id")
	public Instituicao instituicao;

	@ManyToOne
	@JoinColumn(name ="sala_id", referencedColumnName = "id")
	public Sala sala;
	
	@ManyToOne
	@JoinColumn(name = "materia_id", referencedColumnName = "id")
	public Materia materia;

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "Publicacao{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				", tipo=" + tipo +
				", usuario=" + usuario +
				", instituicao=" + instituicao +
				", sala=" + sala +
				", materia=" + materia +
				'}';
	}
}
