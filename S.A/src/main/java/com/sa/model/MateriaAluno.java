package com.sa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class MateriaAluno {

	@EmbeddedId
	MateriaAlunoKey id;
	
	@ManyToOne
	@MapsId("materiaId")
	@JoinColumn(name = "materia_id")
	Materia materia;
	
	@ManyToOne
	@MapsId("alunoId")
	@JoinColumn(name = "aluno_id")
	Aluno aluno;
	
	String dificuldade;

	public MateriaAlunoKey getId() {
		return id;
	}

	public void setId(MateriaAlunoKey id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	
	
}
