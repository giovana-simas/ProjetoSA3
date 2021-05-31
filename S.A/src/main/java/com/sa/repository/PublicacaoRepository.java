package com.sa.repository;

import com.sa.model.Instituicao;
import com.sa.model.Sala;
import com.sa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.model.Publicacao;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{

    public List<Publicacao> findBySalaOrderByIdDesc(Sala sala);
    public List<Publicacao> findByInstituicaoInOrderByIdDesc(List<Instituicao> instituicaos);
    public void deleteByUsuario(Usuario usuario);
}
