package com.sa.repository;

import java.util.List;

import com.sa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{
	
	public List<Instituicao> findByAlunosINot(Aluno aluno);

	public List<Instituicao> findByAlunosI(Aluno aluno);
	public List<Instituicao> findByDiretor(Diretor diretor);
	public List<Instituicao> findByProfessoresI(Professor professor);
	public Instituicao findBySalas(Sala sala);

	public Instituicao findById(long id);
	
}
