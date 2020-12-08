package com.sa.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "D")
public class Diretor extends Usuario{

	@OneToMany(targetEntity=Instituicao.class, mappedBy="diretor")    
	private List<Instituicao> instituicao;

	public List<Instituicao> getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(List<Instituicao> instituicao) {
		this.instituicao = instituicao;
	}
	
	
	
	
	
}
