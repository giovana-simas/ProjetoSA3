package com.sa.controller;

import com.sa.model.Usuario;
import com.sa.repository.SalaRepository;
import com.sa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	SalaRepository salaRepository;
	
	@GetMapping("/chat")
	public String chat(Model model) {
		System.out.println("KARALHUDOS CHEGOU AQ");
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("usuarioConnect",usuarioRepository.findByEmail(email));
		model.addAttribute("usuarios",usuarioRepository.findAllByEmailNot(email));
		

		return "/chat/chat";
	}
	

}
