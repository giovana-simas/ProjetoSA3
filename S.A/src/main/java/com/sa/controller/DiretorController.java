package com.sa.controller;

import com.sa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sa.repository.AlunoRepository;
import com.sa.repository.DiretorRepository;
import com.sa.repository.InstituicaoRepository;
import com.sa.repository.PermissaoRepository;
import com.sa.repository.ProfessorRepository;
import com.sa.repository.SalaRepository;
import com.sa.repository.UsuarioRepository;

@Controller
public class DiretorController {

	
	//autoriza a ultilização do repositorio neste controler
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	DiretorRepository diretorRepository;
	

	@Autowired
	InstituicaoRepository instituicaoRepository;
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	@Autowired
	SalaRepository salaRepository;

	//pega uma informação incapsulada e é chamado atravez do metodo "/cadastro/{salvo}"
	@GetMapping("/diretor/cadastroDiretor/{salvo}")
	//cria o metodo de redirecionamento para a tela de cadastro com um model para registrar as informações do usuario no banco
	public String addDiretor(@PathVariable int salvo, Model model) {
		//adiciona um atributo que pode ser chamado atravez de "usuario" que guarda um novo objeto usuario vazio
		model.addAttribute("diretor", new Diretor());
		//adciona um atributo que pode ser chamado atravez de "salvo" que guarda um numero que irá indicar se o usuario foi salvo ou nao
		//esse numero é setado no Usuario controller
		model.addAttribute("salvo",salvo);
		//retorna a pagina cadastro para o usuario
		return "/diretor/cadastroDiretor";
	}


	
	//incapsula e envia informação e é chamado atravez do metodo "/usuario/save"
	@PostMapping("/diretor/save")
	//cria o metodo de salvamento com um objeto Usuario
	public String saveDiretor(Diretor diretor) {
		//instancia informações que serão usadas
		int salvo = 0;
		String path  = "";
		String email = "";
	
//		verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
		email = SecurityContextHolder.getContext().getAuthentication().getName();
		//inicia uma tentativa
		try {
			//verifica se o objeto usuario não esta vazio
			if(diretor != null) {
				//seta a variavel salvo para 1 onde vai indicar que o usuario foi salvo atravez de um model
				salvo = 1;
				System.out.println(email);
				//confere se há um usuario logado ou se ele esta em "logout" ou seja usuario anonimo.
				//caso seja anonimo o usuario será salvo como novo usuario atravez da tela de cadastro.
				//caso haja um usuario logado, ou seja "email!="anonymousUser"" ele estara editando um usuario ja existente na tela de perfil.
				if(email=="anonymousUser") {
				//pega a senha cadastrada no objeto usuario, ha codifica e aplica no banco sua nova verção codificada
					diretor.setSenha(new BCryptPasswordEncoder().encode(diretor.getSenha()));
				//salva o usuario criado anteriormente em "IndexController" agora com informações preenchidas no banco e mostra as informações salvas no console para conferencia e manutenção
				System.out.print(diretorRepository.save(diretor));
				//seta a variavel "path" para que redirecione para tela de cadastro e mostre se o cadastro foi salvo ou nao
				path  = "redirect:/diretor/cadastroDiretor/" + salvo;
				}else {
					System.out.print(diretor);
					//salva a edição do usuario feito na tela perfil em um usuario ja existente
					System.out.print(diretorRepository.save(diretor));
					//seta a variavel "path" para que redirecione para tela de perfil e mostre se a edição foi salva ou nao
					path  = "redirect:/perfil/" + diretor.getId();
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
				path  = "redirect:diretor/cadastroDiretor/" + salvo;
			}else {
				//seta a variavel "path" para que redirecione para tela de perfil e mostre se a edição foi salva ou nao
				path  = "redirect:/perfil/" + diretor.getId();
			}
		}	
		//redireciona para a tela setada por path
		return path;
	}
	
	//pega uma informação incapsulada e é chamado atravez do metodo "/usuario/perfil/{salvo}"
	@GetMapping("/diretor/perfil/{salvo}")
	//cria o metodo de redirecionamento para a tela de perfil com um model para pegar as informações do usuario logado e enviar para tela
	public String diretorPerfil(Model model,@PathVariable int salvo) {
		//pega o email do usuario logado e quarda na variavel "email"
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		//adiciona um atributo no model pegando a informação do repositoria ultilizando o email logado
		//"usuarioRepository.findByEmail(email)" metodo criado dentro de usuarioRepository para encontrar usuarios atravez do email
		//as informações deste model podem ser puxadas atravez de seu nome ("usuario") e pode ser ultilizada com um th:object="usuario"
		model.addAttribute("diretor", usuarioRepository.findByEmail(email));
		//redireciona para tela de perfil
		return "/diretor/perfil";
		
	}
	

	
	
	

	
}
