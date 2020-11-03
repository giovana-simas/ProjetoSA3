package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sa.model.Usuario;
import com.sa.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	
	@PostMapping("/usuario/save")
	public String saveUsuario(Usuario usuario) {
		boolean salvo = true;
		String path  = "login";
		try {
			if(usuario != null) {
				usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
				System.out.print(usuarioRepository.save(usuario));
				path  = "login";
				
			}
		} catch (Exception e) {
			System.out.print("Erro ao Salvar: " + e.getMessage());
			salvo = false;
			path  = "cadastro";
		}	
		return "redirect:/"+path+"/" + salvo;
	}
	
	@GetMapping("/usuario/perfil")
	public String perfilUsuario(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		model.addAttribute("usuario", usuarioRepository.findByEmail(email));
		
		return "usuario/perfil";
		
	}
	
}
