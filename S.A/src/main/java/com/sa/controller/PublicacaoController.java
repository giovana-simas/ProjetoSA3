package com.sa.controller;

import com.sa.model.*;
import com.sa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;
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
    
    
    @GetMapping("/feed")
	public String feed(Model model) {
		Usuario usuario;
		String email = "";
		Permissao permissao;
		

		email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = usuarioRepository.findByEmail(email);
		permissao = permissaoRepository.findByNome("aluno");

		model.addAttribute("usuario", usuarioRepository.findByEmail(email));

		if(usuario.getPermissoes().contains(permissao)) {
			Aluno aluno;

			aluno = alunoRepository.findByEmail(email);
			model.addAttribute("instituicoes", instituicaoRepository.findByAlunosI(aluno));
			model.addAttribute("aluno", alunoRepository.findByEmail(email));
			model.addAttribute("instituicoesadd", instituicaoRepository.findAll());
            model.addAttribute("publicacao",new Publicacao());
            model.addAttribute("publicacoes",publicacaoRepository.findByInstituicaoIn(aluno.getInstituicoesA()));
			
		}

		permissao = permissaoRepository.findByNome("professor");
		if(usuario.getPermissoes().contains(permissao)) {

			Professor professor;

			professor = professorRepository.findByEmail(email);

			model.addAttribute("instituicoes", instituicaoRepository.findByProfessoresI(professor));
			model.addAttribute("professor", professorRepository.findByEmail(email));
			model.addAttribute("materiasSugerida", materiaSugeridaRepository.findByInstituicaoIn(instituicaoRepository.findByProfessoresI(professor)));
			model.addAttribute("instituicoesadd", instituicaoRepository.findAll());
			model.addAttribute("publicacao",new Publicacao());
            model.addAttribute("publicacoes",publicacaoRepository.findByInstituicaoIn(professor.getInstituicaoP()));

		}

		permissao = permissaoRepository.findByNome("diretor");
		if(usuario.getPermissoes().contains(permissao)) {
			Diretor diretor;

			diretor = diretorRepository.findByEmail(email);
			model.addAttribute("instituicoes", instituicaoRepository.findByDiretor(diretor));
			model.addAttribute("materiasSugerida", materiaSugeridaRepository.findByInstituicaoIn(instituicaoRepository.findByDiretor(diretor)));
			model.addAttribute("diretor", diretorRepository.findByEmail(email));
			model.addAttribute("instituicaoadd", new Instituicao());
			model.addAttribute("publicacao",new Publicacao());
            model.addAttribute("publicacoes",publicacaoRepository.findByInstituicaoIn(diretor.getInstituicao()));
		}
		
		return "feed/feed";
	}

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
