package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sa.model.Usuario;
import com.sa.repository.UsuarioRepository;

@Controller
public class IndexController {

	@Autowired
	UsuarioRepository UsuarioRepository;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/cadastro")
	public String addUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
		return "cadastro";
	}
	
}
