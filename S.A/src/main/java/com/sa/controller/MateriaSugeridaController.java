package com.sa.controller;

import com.sa.model.Materia;
import com.sa.model.MateriaSugerida;
import com.sa.model.Usuario;
import com.sa.repository.InstituicaoRepository;
import com.sa.repository.MateriaRepository;
import com.sa.repository.MateriaSugeridaRepository;
import com.sa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Controller
public class MateriaSugeridaController {

    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MateriaRepository materiaRepository;


    @PostMapping("/materiaSugerida/save")
    public String save(MateriaSugerida materiaSugerida){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(email);
        materiaSugerida.setNome(materiaSugerida.getNome().toLowerCase(Locale.ROOT));
        MateriaSugerida materiaSugerida1 = materiaSugeridaRepository.findByNomeAndInstituicao(materiaSugerida.getNome(), materiaSugerida.getInstituicao());
        Materia materia = materiaRepository.findByNome(materiaSugerida.getNome());

        try{

            if (materiaSugerida1 == null && materia == null ){
                materiaSugeridaRepository.save(materiaSugerida);
            }


        }catch (Exception e){
            System.out.println(e);

        }

        return "redirect:/perfil/" + usuario.getId();
    }

}
