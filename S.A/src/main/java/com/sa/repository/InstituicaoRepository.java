package com.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Aluno;
import com.sa.model.Diretor;
import com.sa.model.Instituicao;
import com.sa.model.Professor;
import com.sa.model.Usuario;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{
	
	public List<Instituicao> findByAlunosINot(Aluno aluno);

	public List<Instituicao> findByAlunosI(Aluno aluno);
	public List<Instituicao> findByDiretor(Diretor diretor);
	public List<Instituicao> findByProfessoresI(Professor professor);
	public Instituicao findById(long id);
	
}
