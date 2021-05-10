package com.sa.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	long id;
	
	@NotNull
	String nome;
	
	@ManyToMany(mappedBy = "materiasLecionadas")
	private List<Professor> professores;
	

	@ManyToMany(mappedBy = "materias")
	private List<Sala> salas;
	
	@OneToMany(mappedBy = "materia")
	private List<MateriaAluno> materiaAluno;
	
	@OneToMany(targetEntity = Publicacao.class, mappedBy = "materia")
	public List<Publicacao> publicacao;

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

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public List<MateriaAluno> getMateriaAluno() {
		return materiaAluno;
	}

	public void setMateriaAluno(List<MateriaAluno> materiaAluno) {
		this.materiaAluno = materiaAluno;
	}
	
	
	
}
