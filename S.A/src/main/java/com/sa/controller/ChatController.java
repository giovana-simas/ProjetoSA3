package com.sa.controller;

import com.sa.model.Chat;
import com.sa.model.Usuario;
import com.sa.model.UsuarioChat;
import com.sa.repository.ChatRepository;
import com.sa.repository.SalaRepository;
import com.sa.repository.UsuarioChatRepository;
import com.sa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioChatRepository usuarioChatRepository;
	@Autowired
	SalaRepository salaRepository;
	@Autowired
	ChatRepository chatRepository;
	
	@GetMapping("/chat")
	public String chat(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("usuarioConnect",usuarioRepository.findByEmail(email));
		model.addAttribute("chats",usuarioChatRepository.findByUsuario1(usuarioRepository.findByEmail(email)));



		return "/chat/chat";
	}

	@GetMapping("/chat/add/{id}")
	public  String addChat(@PathVariable long id){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		String path = "";


		Usuario usuario1 = usuarioRepository.findByEmail(email);
		Usuario usuario2 = usuarioRepository.findById(id);


		UsuarioChat usuarioChat;
		System.out.println("entrou no metodo");
		usuarioChat = usuarioChatRepository.findByUsuario1AndUsuario2(usuario1, usuario2);

		try {
			System.out.println("entrou no try");
			if (usuarioChat == null){
				Chat chat = new Chat();
				chat.addUsuarioChats(usuarioChat);

				usuario1.addUsuarioChats1(usuarioChat);
				usuario2.addUsuarioChats2(usuarioChat);

				usuarioChat.setUsuario1(usuario1);
				usuarioChat.setUsuario2(usuario2);
				usuarioChat.setChat(chat);

				chatRepository.save(chat);

				usuarioRepository.save(usuario1);
				usuarioRepository.save(usuario2);
				System.out.println("chat nulo");

				usuarioChatRepository.save(usuarioChat);
				path = "redirect:/chat";
			}else{
				System.out.println("chat n nulo");
				path = "redirect:/chat";
			}
		}catch (Exception e){
			System.out.println("entrou no catch");
			System.out.println(e);
			path = "redirect:/chat";
		}



		return path;

	}
	

}
