package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.sa.model.MessageModel;
import com.sa.repository.UsuarioRepository;

@RestController
public class MessageController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	
	@MessageMapping("/chat/{email}")
	public void sendMessage(@DestinationVariable String email, MessageModel mensagem) {
		System.out.println("Mensagem: " + mensagem + "Para: " + email);
		
		if(usuarioRepository.findByEmail(email) != null) {
			simpMessagingTemplate.convertAndSend("/topic/mensagens" + email, mensagem);
		}else {
			System.out.println("Usuario não existe");
		}
		
		
	}

}
