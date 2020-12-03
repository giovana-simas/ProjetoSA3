package com.sa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MateriaAlunoKey implements Serializable{

	@Column(name = "aluno_id")
    Long alunoId;
 
    @Column(name = "materia_id")
    Long materiaId;

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public Long getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}
    
    
	
}
