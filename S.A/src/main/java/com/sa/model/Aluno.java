package com.sa.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;



@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Usuario{

	@Size(max=500)
	String descricao;



	@Enumerated(EnumType.STRING)
	@Column(name = "querAjudar", columnDefinition = "naoQuerAjudar")
	private QuerAjudar querAjudar;

	public enum QuerAjudar {
		querAjudar("Quer ajudar"), naoQuerAjudar("Não quer ajudar"), precisaDeAjuda("Precisa de ajuda");

		private final String displayValue;

		private QuerAjudar(String displayValue) {
			this.displayValue = displayValue;
		}

		public String getDisplayValue() {
			return displayValue;
		}
	}


	@OneToMany(mappedBy = "aluno")
	private List<MateriaAluno> materiaAluno;
	
	@ManyToMany
	@JoinTable(
			name="salasA",
			joinColumns=@JoinColumn(name="aluno_id"),
			inverseJoinColumns=@JoinColumn(name="sala_id")
			)
	private List<Sala> salasA;
	
	@ManyToMany
	@JoinTable(
			name="instituicoesA",
			joinColumns=@JoinColumn(name="aluno_id"),
			inverseJoinColumns=@JoinColumn(name="instituicao_id")
			)
	private Set<Instituicao> instituicoesA;


	public QuerAjudar getQuerAjudar() {
		return querAjudar;
	}

	public void setQuerAjudar(QuerAjudar querAjudar) {
		this.querAjudar = querAjudar;
	}

	public List<MateriaAluno> getMateriaAluno() {
		return materiaAluno;
	}

	public void setMateriaAluno(List<MateriaAluno> materiaAluno) {
		this.materiaAluno = materiaAluno;
	}

	

	public List<Sala> getSalasA() {
		return salasA;
	}

	public void setSalasA(List<Sala> salasA) {
		this.salasA = salasA;
	}

	public Set<Instituicao> getInstituicoesA() {
		return instituicoesA;
	}

	public void setInstituicoesA(Set<Instituicao> instituicoesA) {
		this.instituicoesA = instituicoesA;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
