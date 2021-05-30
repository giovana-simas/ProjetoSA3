package com.sa.controller;

import java.util.ArrayList;
import java.util.List;

import com.sa.model.MateriaSugerida;
import com.sa.repository.*;
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

//transforma esta classe em um controller
//controller = classe que faz o intermedio entre pagina web, banco e security.

@Controller
public class UsuarioController {

	@Autowired
	DiretorRepository diretorRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	PermissaoRepository permissaoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuario/delete/{id}")
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


	@GetMapping("/perfil/{id}")
	public String perfil (Model model, @PathVariable long id){
		String path = "";
		Permissao permissao;
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		//verifica o usuario logado e aplica a instancia de conferencia(neste caso é o email do usuario logado) na variavel "email"
		Usuario usuario = usuarioRepository.findById(id);
		System.out.println(usuario);
		permissao = permissaoRepository.findByNome("aluno");

		if (usuario.getPermissoes().contains(permissao)){

			model.addAttribute("aluno", alunoRepository.findById(id).get());
			model.addAttribute("materiaSugerida", new MateriaSugerida());



			path = "aluno/perfil";
		}

		permissao = permissaoRepository.findByNome("professor");
		if (usuario.getPermissoes().contains(permissao)){

			model.addAttribute("professor", professorRepository.findById(id).get());

			path = "professor/perfil";
		}
		permissao = permissaoRepository.findByNome("diretor");
		if (usuario.getPermissoes().contains(permissao)){

			model.addAttribute("diretor", diretorRepository.findById(id).get());

			path = "diretor/perfil";
		}

		if (usuario.getEmail().equals(email)){
			System.out.println("true");
			model.addAttribute("editavel", true);
		}else{
			System.out.println("false");

			model.addAttribute("editavel", false);
		}

		return path;


	}



}
