package com.sa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sa.model.Aluno;
import com.sa.model.Permissao;
import com.sa.model.Usuario;
import com.sa.repository.AlunoRepository;
import com.sa.repository.PermissaoRepository;
import com.sa.repository.ProfessorRepository;
import com.sa.repository.UsuarioRepository;

//transforma esta classe em um controller
//controller = classe que faz o intermedio entre pagina web, banco e security.

@Controller
public class UsuarioController {

	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("usuario/delete/{id}")
	public String deleteUsuario(@PathVariable long id) {
		//pega o email do usuario logado e quarda na variavel "email"
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try {
			usuarioRepository.deleteByEmail(email);
		} catch (Exception e) {
			System.out.print("Erro ao deletar: " + e.getMessage());
		}
		
		return "redirect:/login";
	}
}
