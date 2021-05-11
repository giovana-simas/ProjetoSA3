package com.sa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class MateriaSugerida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    String nome;

    @ManyToOne
    @JoinColumn(name="instituicao_id", referencedColumnName = "id")
    Instituicao instituicao;

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public String toString() {
        return "MateriaSugerida{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
