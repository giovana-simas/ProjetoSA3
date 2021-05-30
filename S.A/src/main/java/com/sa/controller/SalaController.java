package com.sa.controller;

import com.sa.model.*;
import com.sa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SalaController {

    //autoriza a ultilização do repositorio neste controler

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @Autowired
    InstituicaoRepository instituicaoRepository;

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DiretorRepository diretorRepository;

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    PublicacaoRepository publicacaoRepository;

    @PostMapping("/sala/save/{id}")
    //cria o metodo de salvamento com um objeto Usuario
    public String saveSala(Sala sala, @PathVariable int id) {
        System.out.println("sala save");
        //instancia informações que serão usadas
        int salvo = 0;
        Usuario usuario;
        Permissao permissao;

        Instituicao instituicao;
        instituicao = instituicaoRepository.findById(id);
        //inicia uma tentativa
        try {
            //seta a variavel salvo para 1 onde vai indicar que o usuario foi salvo atravez de um model
            salvo = 1;

            sala.setInstituicao(instituicao);
            //salva o usuario criado anteriormente em "IndexController" agora com informações preenchidas no banco e mostra as informações salvas no console para conferencia e manutenção
            salaRepository.save(sala);

        }

        //caso a tentativa falhe o erro sera salvo na variavel "e"
        catch (Exception e) {
            //mostra a mensagem de erro no console para conferencia e manutenção
            System.out.print("Erro ao Salvar: " + e.getMessage());
            //seta a variavel salvo para 2 onde vai indicar que o usuario não foi salvo atravez de um model
            salvo = 2;

        }

        return "redirect:/sala/listSala/" + id;
    }

    @GetMapping("/sala/addsala/{id}")
    public String addSala(Model model, @PathVariable long id) {
        String email = "";
        String path = "";
        Usuario usuario;

        Permissao permissao;
        Instituicao instituicao = instituicaoRepository.findById(id);

        //verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
        email = SecurityContextHolder.getContext().getAuthentication().getName();
        usuario = usuarioRepository.findByEmail(email);
        permissao = permissaoRepository.findByNome("aluno");
        if (usuario.getPermissoes().contains(permissao)){

            System.out.println("id: " + id);
            System.out.println("id: " + instituicao);

            model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("aluno", usuario);
            System.out.println("salas: " + salaRepository.findByInstituicao(instituicao));
            path = "/aluno/addsala";
        }
        permissao = permissaoRepository.findByNome("professor");
        if (usuario.getPermissoes().contains(permissao)){

            System.out.println("id: " + id);
            System.out.println("id: " + instituicao);

            model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("professor", usuario);
            System.out.println("salas: " + salaRepository.findByInstituicao(instituicao));

            path = "/professor/addsala";
        }


        return path;
    }




    @PostMapping("/aluno/insertSala/{id}")
    public String insertSalaAluno(Sala sala,Aluno aluno, @PathVariable int id) {


        String email;
        List<Sala> salaAux;

        email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        salaAux = alunoRepository.findByEmail(email).getSalasA();

        try {
            salaAux.addAll(aluno.getSalasA());
            aluno.setSalasA(salaAux);
            System.out.println(alunoRepository.save(aluno));

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        //instituicao = instituicaoRepository.findById(id)


        return "redirect:/sala/listSala/" + id;

    }

    @PostMapping("/professor/insertsala/{id}")
    public String insertSalaProfessor(Sala sala,Professor professor, @PathVariable int id) {


        String email;
        List<Sala> salaAux;

        email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        salaAux = professorRepository.findByEmail(email).getSalaP();

        try {
            salaAux.addAll(professor.getSalaP());
            professor.setSalaP(salaAux);
            System.out.println(professorRepository.save(professor));

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        //instituicao = instituicaoRepository.findById(id)


        return "redirect:/sala/listSala/" + id;

    }


    @GetMapping("/sala/listSala/{id}")
    public String listSala(Model model,@PathVariable long id) {

        String path = "";
        String email = "";
        Usuario usuario;
        Permissao permissao;

        email = SecurityContextHolder.getContext().getAuthentication().getName();
        Instituicao instituicao = instituicaoRepository.findById(id);
        usuario = usuarioRepository.findByEmail(email);
        permissao = permissaoRepository.findByNome("aluno");

        if (usuario.getPermissoes().contains(permissao)){
            model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
            model.addAttribute("instituicao", instituicao);

            model.addAttribute("aluno", usuario);
            path = "/aluno/listSala";
        }
        permissao = permissaoRepository.findByNome("diretor");
        if (usuario.getPermissoes().contains(permissao)){


            System.out.println("chegou aqui" );
            //verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
            System.out.println("id: " + id);
            System.out.println("id: " + instituicao);
            model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("sala", new Sala());
            System.out.println("salas: " + salaRepository.findByInstituicao(instituicao));

            path = "/diretor/listSala";
        }
        permissao = permissaoRepository.findByNome("professor");
        if (usuario.getPermissoes().contains(permissao)){

            System.out.println("chegou aqui" );
            //verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
            model.addAttribute("professor", usuario);
            model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("sala", new Sala());

            path = "/professor/listSala";
        }



        return path;
    }




    @GetMapping("/sala/{id}")
    public String sala(@PathVariable long id, Model model ) {

        String path = "";
        String email = "";
        Usuario usuario;
        Permissao permissao;

        Sala sala = salaRepository.findById(id);
        email = SecurityContextHolder.getContext().getAuthentication().getName();
        Instituicao instituicao = instituicaoRepository.findBySalas(sala);
        usuario = usuarioRepository.findByEmail(email);
        permissao = permissaoRepository.findByNome("aluno");

        if (usuario.getPermissoes().contains(permissao)){
            Aluno aluno;

            aluno = alunoRepository.findByEmail(email);
            model.addAttribute("usuario", usuario);
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("instituicoes", instituicaoRepository.findByAlunosI(aluno));
            model.addAttribute("alunos", alunoRepository.findBySalasA(sala));
            model.addAttribute("professores", professorRepository.findBySalaP(sala));
            model.addAttribute("sala",sala);
            model.addAttribute("publicacao",new Publicacao());
            model.addAttribute("publicacoes",publicacaoRepository.findBySala(sala));

            model.addAttribute("materias",materiaRepository.findAll());

            path = "/aluno/sala";
        }
        permissao = permissaoRepository.findByNome("diretor");
        if (usuario.getPermissoes().contains(permissao)){

            Diretor diretor;

            diretor = diretorRepository.findByEmail(email);
            model.addAttribute("instituicoes", instituicaoRepository.findByDiretor(diretor));
            model.addAttribute("usuario", usuario);
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("sala",sala);
            model.addAttribute("alunos", alunoRepository.findBySalasA(sala));
            model.addAttribute("professores", professorRepository.findBySalaP(sala));
            model.addAttribute("publicacao",new Publicacao());
            model.addAttribute("publicacoes",publicacaoRepository.findBySala(sala));
            
            model.addAttribute("materias",materiaRepository.findAll());
            
            path = "/diretor/sala";
        }
        permissao = permissaoRepository.findByNome("professor");
        if (usuario.getPermissoes().contains(permissao)){

            Professor professor;

            professor = professorRepository.findByEmail(email);
            model.addAttribute("usuario", usuario);
            model.addAttribute("instituicoes", instituicaoRepository.findByProfessoresI(professor));
            model.addAttribute("instituicao", instituicao);
            model.addAttribute("alunos", alunoRepository.findBySalasA(sala));
            model.addAttribute("professores", professorRepository.findBySalaP(sala));
            model.addAttribute("sala",sala);
            model.addAttribute("publicacao",new Publicacao());
            model.addAttribute("publicacoes",publicacaoRepository.findBySala(sala));

            model.addAttribute("materias",materiaRepository.findAll());
            
            path = "/professor/sala";
        }



        return path;
    }



    @GetMapping("/sala/criaSala/{id}")
    public String criaSala(Model model,@PathVariable long id) {
        String path = "";
        String email = "";
        Usuario usuario;
        Permissao permissao;
        email = SecurityContextHolder.getContext().getAuthentication().getName();

        usuario = usuarioRepository.findByEmail(email);
        permissao = permissaoRepository.findByNome("professor");

        if (usuario.getPermissoes().contains(permissao)) {

            model.addAttribute("sala", new Sala());
            model.addAttribute("instituicao", instituicaoRepository.findById(id));

            path = "/professor/criaSala";
        }
        permissao = permissaoRepository.findByNome("diretor");
        if (usuario.getPermissoes().contains(permissao)){
            model.addAttribute("sala", new Sala());
            model.addAttribute("instituicao", instituicaoRepository.findById(id));

            path = "/diretor/addsala";
        }
        return path;
    }

}