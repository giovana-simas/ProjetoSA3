package com.sa.repository;

import com.sa.model.Instituicao;
import com.sa.model.MateriaSugerida;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Materia;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    public Materia findByNome(String nome);

}
