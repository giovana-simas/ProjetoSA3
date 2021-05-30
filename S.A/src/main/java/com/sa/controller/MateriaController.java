package com.sa.controller;

import com.sa.model.Materia;
import com.sa.model.MateriaSugerida;
import com.sa.model.Usuario;
import com.sa.repository.DiretorRepository;
import com.sa.repository.MateriaRepository;
import com.sa.repository.MateriaSugeridaRepository;
import com.sa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class MateriaController {

    @Autowired
    MateriaRepository materiaRepository;
    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;




}
