package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	
	
	@PostMapping("usuario/save")
	public String saveUsuario(Usuario usuario) {
		
		try {
			if(usuario != null) {
				usuarioRepository.save(usuario);
			}
		} catch (Exception e) {
			System.out.print("Erro ao Salvar: " + e.getMessage());
		}	
		return "redirect:/login";
	}
	
	@GetMapping("usuario/perfil")
	public String perfilUsuario(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		model.addAttribute("usuario", usuarioRepository.findByEmail(email));
		
		return "usuario/perfil";
		
	}
	
}