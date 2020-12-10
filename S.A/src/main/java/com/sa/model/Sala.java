package com.sa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class Sala{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@NonNull
	@Size(max=255)
	String nome;
	
	
	
	@ManyToMany
	@JoinTable(
			name="sala_materia",
			joinColumns=@JoinColumn(name="sala_id"),
			inverseJoinColumns=@JoinColumn(name="materia_id")
			)
	private List<Materia> materias;	
	
	
	@ManyToMany(mappedBy = "salasA")
	private List<Aluno> alunoS;
	
	@ManyToMany
	@JoinTable(
			name="sala_professor",
			joinColumns=@JoinColumn(name="sala_id"),
			inverseJoinColumns=@JoinColumn(name="professor_id")
			) 
	private List<Professor> professores;
	
	@ManyToOne()
	@JoinColumn(name="instituicao_id", referencedColumnName = "id")    
	private Instituicao instituicao;

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

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	

	public List<Aluno> getAlunoS() {
		return alunoS;
	}

	public void setAlunoS(List<Aluno> alunoS) {
		this.alunoS = alunoS;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	
	
}
