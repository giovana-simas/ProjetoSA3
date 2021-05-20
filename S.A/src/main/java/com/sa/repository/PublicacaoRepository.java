package com.sa.repository;

import com.sa.model.Instituicao;
import com.sa.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Publicacao;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{

    public List<Publicacao> findBySala(Sala sala);
    public List<Publicacao> findByInstituicaoIn(List<Instituicao> instituicaos);
}
