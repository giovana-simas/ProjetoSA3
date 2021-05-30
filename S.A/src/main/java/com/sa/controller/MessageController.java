package com.sa.controller;

import com.sa.model.Chat;
import com.sa.model.Usuario;
import com.sa.model.UsuarioChat;
import com.sa.repository.ChatRepository;
import com.sa.repository.MessageRepository;
import com.sa.repository.UsuarioChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sa.model.Message;
import com.sa.repository.UsuarioRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("api")
public class MessageController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UsuarioChatRepository usuarioChatRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@RequestMapping("/chat")
	public ModelAndView chat (Model model) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("chat/chat");
	    
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("usuarioConnect",usuarioRepository.findByEmail(email));
		model.addAttribute("chats",usuarioChatRepository.findByUsuario1OrUsuario2(usuarioRepository.findByEmail(email),usuarioRepository.findByEmail(email)));
	    return modelAndView;
	}


	@MessageMapping("/chat/{id}")
	public void sendMessage(@DestinationVariable long id, Message mensagem) {
		System.out.println("MensagemController: " + mensagem.getMensagem() + " De: " + mensagem.getFromLogin() + " Para: " + id);
		System.out.println("antes do try");
		try {
			System.out.println("entrou no try");
			Usuario usuario1 = usuarioRepository.findById(Long.parseLong(mensagem.getFromLogin()) );
			Usuario usuario2 = usuarioRepository.findById(id);
			System.out.println("criou usuario 2 " +usuario1 +"\n" +usuario1);

			UsuarioChat usuarioChat = null;

			usuarioChat = usuarioChatRepository.findByUsuario1AndUsuario2OrUsuario2AndUsuario1(usuario1,usuario2,usuario1,usuario2);


			System.out.println(usuarioChat);
			Chat chat = null;
			if (usuarioChat==null){

					System.out.println("chat n encontrado");


			}else if (usuarioChat!=null){
				 chat = chatRepository.findByUsuarioChats(usuarioChat);
				System.out.println(usuarioChat);
			}


			mensagem.setChat(chat);

			messageRepository.findByChat(chat);
			chat.addMenssage(messageRepository.save(mensagem));
			chatRepository.save(chat);

		}catch (Exception e){

			System.out.println("entrou no catch\n" + e);
		}

		if(usuarioRepository.findById(id) != null) {
			simpMessagingTemplate.convertAndSend("/topic/mensagens/" + id, mensagem);
		}else {
			System.out.println("Usuario não existe");
		}
		
		
	}


	@GetMapping(path = "/chat/list/{id}")
	public @ResponseBody ResponseEntity<Iterable<Message>> list(HttpServletResponse response, @PathVariable("id") long id, Message message1) {
		Chat chat = chatRepository.findById(id);
		try {
			System.out.println("passou por aqui");

			Iterable<Message> message = messageRepository.findByChat(chat);

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");

			if (((Collection<?>) message).size() == 0) {
				System.out.println(message);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				System.out.println(message);
				return new ResponseEntity<>(message,HttpStatus.OK );
			}

		} catch(Exception e) {
			System.out.println("catch");
			return new  ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}


	}

}
