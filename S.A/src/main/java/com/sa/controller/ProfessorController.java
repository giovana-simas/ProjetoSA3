package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sa.model.Aluno;
import com.sa.model.Professor;
import com.sa.repository.AlunoRepository;
import com.sa.repository.InstituicaoRepository;
import com.sa.repository.PermissaoRepository;
import com.sa.repository.ProfessorRepository;

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
					path  = "redirect:/professor/cadastroProfessor" + salvo;
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
					path  = "redirect:/professor/cadastroProfessor" + salvo;
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
			model.addAttribute("instituicoes", instituicaoRepository.findByProfessores(professor));

			
			return "/professor/instituicao";
		}
		
		
}
