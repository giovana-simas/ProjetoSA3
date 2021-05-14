package com.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Aluno;
import com.sa.model.Instituicao;
import com.sa.model.Sala;
import com.sa.model.Usuario;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	public Aluno findByEmail(String email);
	public List<Aluno> findBySalasA(Sala sala);
}
