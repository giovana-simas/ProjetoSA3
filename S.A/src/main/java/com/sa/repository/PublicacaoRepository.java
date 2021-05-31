package com.sa.repository;

import com.sa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{

    public List<Publicacao> findBySalaOrderByIdDesc(Sala sala);
    public List<Publicacao> findByInstituicaoInOrderByIdDesc(List<Instituicao> instituicaos);
    public void deleteByUsuario(Usuario usuario);
    public List<Publicacao> findByUsuario(Usuario usuario);
    public Publicacao findById(long id);
    public List<Publicacao> findByMateria(Materia materia);
}
