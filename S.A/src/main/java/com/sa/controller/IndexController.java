package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sa.model.Aluno;
import com.sa.model.Diretor;
import com.sa.model.Instituicao;
import com.sa.model.Professor;
import com.sa.model.Usuario;
import com.sa.repository.InstituicaoRepository;
import com.sa.repository.UsuarioRepository;

//transforma esta classe em um controller
//controller = classe que faz o intermedio entre pagina web, banco e security.
@Controller
public class IndexController {

	//autoriza a ultilização do repositorio neste controler
	@Autowired
	UsuarioRepository UsuarioRepository;
	@Autowired
	InstituicaoRepository instituicaoRepository;
	
	//pega uma informação incapsulada e é chamado atravez do metodo "/"
	@GetMapping("/")
	public String index(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		if(email == "anonymousUser"){
			model.addAttribute("logado", false);
		}else {
			model.addAttribute("logado", true);
		}


		//retorna a pagina index para o usuario
		return "/index";
	}
	
	
	//pega uma informação incapsulada e é chamado atravez do metodo "/login"
	@GetMapping("/login")
	public String getLogin() {
		//retorna a pagina login para o usuario
		return "/login";
	}
	

	

	
		

		
	
}
