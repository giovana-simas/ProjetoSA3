package com.sa.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Usuario{

	
	String querAjudar;
	

	@OneToMany(mappedBy = "aluno")
	private List<MateriaAluno> materiaAluno;
	
	@ManyToMany(mappedBy = "alunos")
	private List<Sala> salas;
	
	@ManyToMany(mappedBy = "alunos")
	private List<Instituicao> instituicoes;

	

	public String getQuerAjudar() {
		return querAjudar;
	}

	public void setQuerAjudar(String querAjudar) {
		this.querAjudar = querAjudar;
	}

	public List<MateriaAluno> getMateriaAluno() {
		return materiaAluno;
	}

	public void setMateriaAluno(List<MateriaAluno> materiaAluno) {
		this.materiaAluno = materiaAluno;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}
	
	
}
