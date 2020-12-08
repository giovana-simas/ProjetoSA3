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

import com.sun.istack.NotNull;

@Entity
public class Instituicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@NonNull
	@Size(max=255)
	String nome;

	@NonNull
	String endereco;
	
	
	String numeroContato;
	
	@OneToMany(targetEntity=Sala.class, mappedBy="instituicao")    
	private List<Sala> salas;
	
	@ManyToOne()
	@JoinColumn(name="diretor_id", referencedColumnName = "id")    
	private Diretor diretor;
	
	@ManyToMany
	@JoinTable(
			name="instituicao_professor",
			joinColumns=@JoinColumn(name="instituicao_id"),
			inverseJoinColumns=@JoinColumn(name="professor_id")
			)   
	private List<Professor> professores;
	
	@ManyToMany
	@JoinTable(
			name="instituicao_aluno",
			joinColumns=@JoinColumn(name="instituicao_id"),
			inverseJoinColumns=@JoinColumn(name="aluno_id")
			)
	private List<Aluno> alunos;
	
	

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
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






	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	

	

	public String getNumeroContato() {
		return numeroContato;
	}

	public void setNumeroContato(String numeroContato) {
		this.numeroContato = numeroContato;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
	
	
}
