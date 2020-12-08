package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Aluno;
import com.sa.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	public Professor findByEmail(String email);

}
