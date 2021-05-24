package com.sa.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;



@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Usuario{





	@Enumerated(EnumType.STRING)
	@Column(name = "statusAjuda", columnDefinition = "varchar(32) default 'naoQuerAjudar'")
	public StatusAjuda statusAjuda = StatusAjuda.naoQuerAjudar;

	public enum StatusAjuda {
		querAjudar("Quer ajudar"), naoQuerAjudar("Não quer ajudar"), precisaDeAjuda("Precisa de ajuda");

		public final String displayValue;

		private StatusAjuda(String displayValue) {
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
	private List<Instituicao> instituicoesA;

	public StatusAjuda getStatusAjuda() {
		return statusAjuda;
	}

	public void setStatusAjuda(StatusAjuda statusAjuda) {
		this.statusAjuda = statusAjuda;
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

	public List<Instituicao> getInstituicoesA() {
		return instituicoesA;
	}

	public void setInstituicoesA(List<Instituicao> instituicoesA) {
		this.instituicoesA = instituicoesA;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
