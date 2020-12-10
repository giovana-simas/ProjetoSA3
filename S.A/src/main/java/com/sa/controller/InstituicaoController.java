package com.sa.controller;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sa.model.Aluno;
import com.sa.model.Diretor;
import com.sa.model.Instituicao;
import com.sa.model.Permissao;
import com.sa.model.Professor;
import com.sa.model.Usuario;
import com.sa.repository.AlunoRepository;
import com.sa.repository.DiretorRepository;
import com.sa.repository.InstituicaoRepository;
import com.sa.repository.PermissaoRepository;
import com.sa.repository.ProfessorRepository;
import com.sa.repository.SalaRepository;
import com.sa.repository.UsuarioRepository;

import javassist.expr.NewArray;

//transforma esta classe em um controller
//controller = classe que faz o intermedio entre pagina web, banco e security.

@Controller
public class InstituicaoController {

	
	//autoriza a ultilização do repositorio neste controler
		@Autowired
		UsuarioRepository usuarioRepository;
		
		@Autowired
		ProfessorRepository professorRepository;
		
		@Autowired
		AlunoRepository alunoRepository;
		
		
		@Autowired
		PermissaoRepository permissaoRepository;
		
		@Autowired
		SalaRepository salaRepository;
		
		@Autowired
		InstituicaoRepository instituicaoRepository;
		

		@Autowired
		DiretorRepository diretorRepository;
		
		@GetMapping("/instituicao")
		public String listUsuario( ) {
			String path = "";
			Usuario usuario;
			String email = "";
			Permissao permissao;
			
//			verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			usuario = usuarioRepository.findByEmail(email);
			permissao = permissaoRepository.findByNome("padrao");
	System.out.println("chegou na conferencia");
			if(usuario.getPermissoes().contains(permissao)) {
				path = "redirect:/aluno/instituicao";
				
			}
			permissao = permissaoRepository.findByNome("professor");
			if(usuario.getPermissoes().contains(permissao)) {
				path = "redirect:/professor/instituicao";
			}
			permissao = permissaoRepository.findByNome("diretor");
			if(usuario.getPermissoes().contains(permissao)) {
				path = "redirect:/diretor/instituicao";
			}
			
			System.out.println(path);

			return path;
			
		}
		
		@GetMapping("/diretor/addinstituicao")
		public String addInstituicao(Model model) {
			String email ="";
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			
			model.addAttribute("instituicao", new Instituicao());
			model.addAttribute("diretor", diretorRepository.findByEmail(email));
			
			return "/diretor/addinstituicao";
		}
		
		@PostMapping("/instituicao/save")
		//cria o metodo de salvamento com um objeto Usuario
		public String saveInstituicao(Instituicao instituicao) {
			//instancia informações que serão usadas
			int salvo = 0;
			String path  = "";
			String email ="";
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			Diretor diretor;
			diretor = diretorRepository.findByEmail(email);
			//inicia uma tentativa
			try {
					//seta a variavel salvo para 1 onde vai indicar que o usuario foi salvo atravez de um model
					salvo = 1;
					
					instituicao.setDiretor(diretor);
					//salva o usuario criado anteriormente em "IndexController" agora com informações preenchidas no banco e mostra as informações salvas no console para conferencia e manutenção
					System.out.print(instituicaoRepository.save(instituicao));
					//seta a variavel "path" para que redirecione para tela de cadastro e mostre se o cadastro foi salvo ou nao
					path  = "redirect:/diretor/instituicao"; //+ salvo;
					
				
			}
			
			//caso a tentativa falhe o erro sera salvo na variavel "e"
			catch (Exception e) {
				//mostra a mensagem de erro no console para conferencia e manutenção
				System.out.print("Erro ao Salvar: " + e.getMessage());
				//seta a variavel salvo para 2 onde vai indicar que o usuario não foi salvo atravez de um model
				salvo = 2;
				//confere se há um usuario logado ou se ele esta em "logout" ou seja usuario anonimo.
				//caso seja anonimo mostrara uma mensagem de erro tela de cadastro.
				//caso haja um usuario logado, ou seja "email!="anonymousUser"" ele mostrara uma mensagem de erro na tela de perfil.
				path  = "redirect:/diretor/instituicao"; //+ salvo;
			}	
			//redireciona para a tela setada por path
			return path;
		}
		
		
		
		@GetMapping("/aluno/addinstituicao")
		public String addinstituicaoAluno(Model model) {
			
			String email = "";
			Aluno aluno;
			
//			verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			aluno = alunoRepository.findByEmail(email);
			
			model.addAttribute("instituicoes", instituicaoRepository.findAll());
			model.addAttribute("aluno", alunoRepository.findByEmail(email));
			System.out.println(instituicaoRepository.findByAlunosI(aluno));
			return "/aluno/addinstituicao";
			
		}
		
		@PostMapping("/aluno/insertinstituicao")
		public String addAlunoInstituicao(Instituicao instituicoes,Aluno aluno) {
			
			
			String email;
			Set<Instituicao> instituicoesAux;
			
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			System.out.println(email);
			instituicoesAux = alunoRepository.findByEmail(email).getInstituicoesA();

			try {
				instituicoesAux.addAll(aluno.getInstituicoesA());
				aluno.setInstituicoesA(instituicoesAux);
				System.out.println(alunoRepository.save(aluno));
				
			} catch (Exception e) {
				System.out.println("error: " + e);
			}
			//instituicao = instituicaoRepository.findById(id)
			
			
			return "redirect:/aluno/instituicao";
			
		}
		
		@GetMapping("/professor/addinstituicao")
		public String addinstituicaoProfessor(Model model) {
			
			String email = "";
			Professor professor;
			
//			verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			professor = professorRepository.findByEmail(email);
			
			model.addAttribute("instituicoes", instituicaoRepository.findAll());
			model.addAttribute("professor", professorRepository.findByEmail(email));
			return "/professor/addinstituicao";
			
		}
		
		@PostMapping("/professor/insertinstituicao")
		public String addProfessorInstituicao(Instituicao instituicoes,Professor professor) {
			
			
			String email;
			List<Instituicao> instituicoesAux;
			
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			System.out.println(email);
			instituicoesAux = professorRepository.findByEmail(email).getInstituicaoP();

			try {
				instituicoesAux.addAll(professor.getInstituicaoP());
				professor.setInstituicaoP(instituicoesAux);
				System.out.println(professorRepository.save(professor));
				
			} catch (Exception e) {
				System.out.println("error: " + e);
			}
			//instituicao = instituicaoRepository.findById(id)
			
			
			return "redirect:/professor/instituicao";
			
		}
		
		
}
