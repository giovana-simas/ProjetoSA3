package com.sa.repository;

import java.util.List;
import java.util.Optional;

import com.sa.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Aluno;
import com.sa.model.Instituicao;
import com.sa.model.Sala;

public interface SalaRepository extends JpaRepository<Sala,Long>{

	public List<Sala> findByInstituicao(Instituicao instituicao);
	public List<Sala> findByInstituicaoAndAlunoSNotContains(Instituicao instituicao,Aluno aluno);
	public List<Sala> findByInstituicaoAndProfessoresSNotContains(Instituicao instituicao, Professor professor);
	public List<Sala> findByInstituicaoIn(List<Instituicao> instituicoes);
	public Sala findById(long id);
	public List<Sala> findByAlunoS(Aluno aluno);
}
