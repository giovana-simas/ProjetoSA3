package com.sa.controller;

import com.sa.model.*;
import com.sa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    DiretorRepository diretorRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    PermissaoRepository permissaoRepository;
    @Autowired
    InstituicaoRepository instituicaoRepository;
    @Autowired
    PublicacaoRepository publicacaoRepository;
    @Autowired
    SalaRepository salaRepository;

    @PostMapping("/publicacaoSala/save/{id}")
    public String savePublicao(@PathVariable long id,Publicacao publicacao){

        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        String path="";

        Usuario usuario;
        Permissao permissao;

        //verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
        email = SecurityContextHolder.getContext().getAuthentication().getName();
        usuario = usuarioRepository.findByEmail(email);
        Sala sala = salaRepository.findById(id);
        permissao = permissaoRepository.findByNome("aluno");



        try {
            publicacao.setSala(sala);
            publicacao.setInstituicao(sala.getInstituicao());
            publicacao.setUsuario(usuario);
            publicacaoRepository.save(publicacao);

        }catch (Exception e){
            System.out.println(e);
        }



        return "redirect:/sala/" +id;
    }

}
