package com.sa.repository;

import com.sa.model.Instituicao;
import com.sa.model.MateriaSugerida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaSugeridaRepository extends JpaRepository<MateriaSugerida,Long> {

    public MateriaSugerida findByNomeAndInstituicao(String nome, Instituicao instituicao);
    public List<MateriaSugerida> findByInstituicaoIn(List<Instituicao> instituicao);
    public MateriaSugerida findById(long id);
}
