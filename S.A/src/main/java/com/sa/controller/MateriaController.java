package com.sa.controller;


import com.sa.model.Materia;
import com.sa.model.MateriaSugerida;
import com.sa.repository.MateriaRepository;
import com.sa.repository.MateriaSugeridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    MateriaRepository materiaRepository;
    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;

    @GetMapping(value="/posts")
    public ResponseEntity<List<MateriaSugerida>> all() {
        return ResponseEntity.ok().body(materiaSugeridaRepository.findAll());
    }

    @PostMapping(value = "/materia/{id}",produces = "application/json")
    public ResponseEntity<Long> deletePost(@PathVariable Long id) {

        materiaSugeridaRepository.deleteById(id);

        if (materiaSugeridaRepository.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
