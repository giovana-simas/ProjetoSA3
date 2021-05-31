package com.sa.controller;


import com.sa.model.Diretor;
import com.sa.model.Materia;
import com.sa.model.MateriaSugerida;
import com.sa.model.Usuario;
import com.sa.repository.DiretorRepository;
import com.sa.repository.MateriaRepository;
import com.sa.repository.MateriaSugeridaRepository;
import com.sa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MateriaRestController {

    @Autowired
    MateriaRepository materiaRepository;
    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;
    @Autowired
    DiretorRepository diretorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;





    @GetMapping(value = "/materia/{id}",produces = "application/json")
    public ResponseEntity<?> deleteMateriaSugerida(@PathVariable Long id) {



        materiaSugeridaRepository.deleteById(id);

        if (materiaSugeridaRepository.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping(value = "/materia/convert/{id}",produces = "application/json")
    public ResponseEntity<?> converteMateria(@PathVariable long id) {
        Materia materia = new Materia();
        MateriaSugerida materiaSugerida = materiaSugeridaRepository.findById(id);

        if (materiaSugeridaRepository.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        materia.setNome(materiaSugerida.getNome());
        materiaSugeridaRepository.deleteById(id);
        materiaRepository.save(materia);

        return new ResponseEntity<>( HttpStatus.OK);
    }




}
