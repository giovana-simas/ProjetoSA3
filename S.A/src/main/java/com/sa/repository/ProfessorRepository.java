package com.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Aluno;
import com.sa.model.Professor;
import com.sa.model.Sala;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	public Professor findByEmail(String email);
	public List<Professor> findBySalaP(Sala sala);

}
