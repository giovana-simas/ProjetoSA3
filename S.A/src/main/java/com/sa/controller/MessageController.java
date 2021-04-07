package com.sa.controller;

import com.sa.repository.ChatRepository;
import com.sa.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.sa.model.Message;
import com.sa.repository.UsuarioRepository;

@RestController
public class MessageController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	
	@MessageMapping("/chat/{id}")
	public void sendMessage(@DestinationVariable long id, Message mensagem) {
		System.out.println("MensagemController: " + mensagem.getMensagem() + " De: " + mensagem.getFromLogin() + " Para: " + id);

		try {
			messageRepository.save(mensagem);
		}catch (Exception e){
			System.out.println(e);
		}

		if(usuarioRepository.findById(id) != null) {
			simpMessagingTemplate.convertAndSend("/topic/mensagens/" + id, mensagem);
		}else {
			System.out.println("Usuario não existe");
		}
		
		
	}

}
