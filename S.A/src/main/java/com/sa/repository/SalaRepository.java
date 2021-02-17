package com.sa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Instituicao;
import com.sa.model.Sala;

public interface SalaRepository extends JpaRepository<Sala,Long>{

	public List<Sala> findByInstituicao(Instituicao instituicao);
	public Sala findById(long id);
}
