package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sa.model.Aluno;
import com.sa.model.Usuario;
import com.sa.repository.UsuarioRepository;

//transforma esta classe em um controller
//controller = classe que faz o intermedio entre pagina web, banco e security.
@Controller
public class IndexController {

	//autoriza a ultilização do repositorio neste controler
	@Autowired
	UsuarioRepository UsuarioRepository;
	
	//pega uma informação incapsulada e é chamado atravez do metodo "/"
	@GetMapping("/")
	public String index() {
		//retorna a pagina index para o usuario
		return "/index";
	}
	//pega uma informação incapsulada e é chamado atravez do metodo "/login"
	@GetMapping("/login")
	public String getLogin() {
		//retorna a pagina login para o usuario
		return "/login";
	}
	//pega uma informação incapsulada e é chamado atravez do metodo "/cadastro/{salvo}"
	@GetMapping("/cadastro/{salvo}")
	//cria o metodo de redirecionamento para a tela de cadastro com um model para registrar as informações do usuario no banco
	public String addUsuario(@PathVariable int salvo, Model model) {
		//adiciona um atributo que pode ser chamado atravez de "usuario" que guarda um novo objeto usuario vazio
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("aluno", new Aluno());
		//adciona um atributo que pode ser chamado atravez de "salvo" que guarda um numero que irá indicar se o usuario foi salvo ou nao
		//esse numero é setado no Usuario controller
		model.addAttribute("salvo",salvo);
		//retorna a pagina cadastro para o usuario
		return "/cadastro";
	}
	
}
