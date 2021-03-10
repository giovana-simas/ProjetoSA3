package com.sa.controller;

import com.sa.model.Usuario;
import com.sa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/chat")
	public String chat(Model model) {
		model.addAttribute("usuarios",usuarioRepository.findAll());

		return "/chat/chat";
	}
	

}
