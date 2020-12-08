package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Aluno;
import com.sa.model.Diretor;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
	public Diretor findByEmail(String email);

}
