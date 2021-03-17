package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.model.Message;
import com.sa.repository.UsuarioRepository;

@RestController
public class MessageController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	
	@MessageMapping("/chat/{id}")
	public void sendMessage(@DestinationVariable long id, Message mensagem) {
		System.out.println("Mensagem: " + mensagem + "Para: " + id);
		
		if(usuarioRepository.findById(id) != null) {
			simpMessagingTemplate.convertAndSend("/topic/mensagens/" + id, mensagem);
		}else {
			System.out.println("Usuario não existe");
		}
		
		
	}

}
