package com.sa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sa.model.Instituicao;
import com.sa.model.Professor;
import com.sa.model.Sala;
import com.sa.repository.AlunoRepository;
import com.sa.repository.InstituicaoRepository;
import com.sa.repository.PermissaoRepository;
import com.sa.repository.ProfessorRepository;
import com.sa.repository.SalaRepository;

@Controller
public class ProfessorController {

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
		
		//incapsula e envia informação e é chamado atravez do metodo "/usuario/save"
		@PostMapping("/professor/save")
		//cria o metodo de salvamento com um objeto Usuario
		public String saveUsuario(Professor professor) {
			//instancia informações que serão usadas
			int salvo = 0;
			String path  = "";
			String email = "";
		
			
//			verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			//inicia uma tentativa
			try {
				//verifica se o objeto usuario não esta vazio
				if(professor != null) {
					//seta a variavel salvo para 1 onde vai indicar que o usuario foi salvo atravez de um model
					salvo = 1;
					//confere se há um usuario logado ou se ele esta em "logout" ou seja usuario anonimo.
					//caso seja anonimo o usuario será salvo como novo usuario atravez da tela de cadastro.
					//caso haja um usuario logado, ou seja "email!="anonymousUser"" ele estara editando um usuario ja existente na tela de perfil.
					if(email=="anonymousUser") {
					//pega a senha cadastrada no objeto usuario, ha codifica e aplica no banco sua nova verção codificada
						professor.setSenha(new BCryptPasswordEncoder().encode(professor.getSenha()));
					//salva o usuario criado anteriormente em "IndexController" agora com informações preenchidas no banco e mostra as informações salvas no console para conferencia e manutenção
					System.out.print(professorRepository.save(professor));
					//seta a variavel "path" para que redirecione para tela de cadastro e mostre se o cadastro foi salvo ou nao
					path  = "redirect:/professor/cadastroProfessor/" + salvo;
					}else {
						System.out.print(professor);
						//salva a edição do usuario feito na tela perfil em um usuario ja existente
						System.out.print(professorRepository.save(professor));
						//seta a variavel "path" para que redirecione para tela de perfil e mostre se a edição foi salva ou nao
						path  = "redirect:/professor/perfil/" + salvo;
					}
					
				}
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
				if(email=="anonymousUser") {
					//seta a variavel "path" para que redirecione para tela de cadastro e mostre se o cadastro foi salvo ou nao
					path  = "redirect:/professor/cadastroProfessor/" + salvo;
				}else {
					//seta a variavel "path" para que redirecione para tela de perfil e mostre se a edição foi salva ou nao
					path  = "redirect:/professor/perfil/" + salvo;
				}
			}	
			//redireciona para a tela setada por path
			return path;
		}
		
		@GetMapping("/professor/instituicao")
		public String professorInstituicao(Model model) {
			String email = "";
			Professor professor;
			
//			verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			professor = professorRepository.findByEmail(email);
			model.addAttribute("instituicoes", instituicaoRepository.findByProfessoresI(professor));

			
			return "/professor/instituicao";
		}
		
		@GetMapping("/professor/listSala/{id}")
		public String listSalaProfessor(Model model,@PathVariable long id) {
			
			Instituicao instituicao = instituicaoRepository.findById(id);
			System.out.println("chegou aqui" );
			//verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			
			model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
			model.addAttribute("instituicao", instituicao);
			
			
			return "/professor/listSala";
		}
		
		
		@GetMapping("/professor/addsala/{id}")
		public String addProfessor(Model model,@PathVariable long id) {
			
			String email = "";
			Professor professor;
			Instituicao instituicao = instituicaoRepository.findById(id);
//			verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			professor = professorRepository.findByEmail(email);
			
			System.out.println("id: " + id);
			System.out.println("id: " + instituicao);
			
			model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));
			model.addAttribute("instituicao", instituicao);
			model.addAttribute("professor", professor);
			System.out.println("salas: " + salaRepository.findByInstituicao(instituicao));
			return "/professor/addsala";
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
			
			
			return "redirect:/professor/listSala/" + id;
			
		}
		
		@GetMapping("/professor/perfil/{salvo}")
		public String perfilProfessor(Model model, @PathVariable int salvo ) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addAttribute("professor", professorRepository.findByEmail(email));
			
			return "/professor/perfil";
		}
		
		@GetMapping("/professor/sala/{id}")
		public String sala(@PathVariable long id, Model model ) {
			

			Sala sala = salaRepository.findById(id);
			Instituicao instituicao = instituicaoRepository.findBySalas(sala);
			
			model.addAttribute("instituicao", instituicao);
			model.addAttribute("alunos", alunoRepository.findBySalasA(sala));
			model.addAttribute("professores", professorRepository.findBySalaP(sala));
			
			return "/professor/sala";
		}

	@GetMapping("/professor/criaSala/{id}")
	public String criaSala(Model model,@PathVariable long id) {

		model.addAttribute("sala", new Sala());
		model.addAttribute("instituicao", instituicaoRepository.findById(id));

		return "/professor/criaSala";
	}

	@PostMapping("/professor/saiSala/{id}")
	public  String saiSala(Model model, @PathVariable long id){

		Instituicao instituicao = instituicaoRepository.findById(id);
		model.addAttribute("salas", salaRepository.findByInstituicao(instituicao));


			return "/professor/saiSala";
	}


}
