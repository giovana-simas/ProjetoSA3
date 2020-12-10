package com.sa.model;

import java.util.List;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	
	@ManyToMany
	@JoinTable(
			name="instituicoesA",
			joinColumns=@JoinColumn(name="aluno_id"),
			inverseJoinColumns=@JoinColumn(name="instituicao_id")
			)
	private Set<Instituicao> instituicoesA;

	

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

	public Set<Instituicao> getInstituicoesA() {
		return instituicoesA;
	}

	public void setInstituicoesA(Set<Instituicao> instituicoesA) {
		this.instituicoesA = instituicoesA;
	}

	
	
}
