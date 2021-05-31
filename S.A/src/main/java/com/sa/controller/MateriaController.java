package com.sa.controller;

import com.sa.model.Materia;
import com.sa.model.MateriaSugerida;
import com.sa.model.Publicacao;
import com.sa.model.Usuario;
import com.sa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Controller
public class MateriaController {

    @Autowired
    MateriaRepository materiaRepository;
    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PublicacaoRepository publicacaoRepository;


    @PostMapping("/materia/save")
    public String saveMateria(Materia materia){

        Materia materia1 = materiaRepository.findByNome(materia.getNome().toLowerCase(Locale.ROOT));

        try {
            if (materia1==null){
                materiaRepository.save(materia);
            }


        }catch (Exception e){
            System.out.println(e);
        }
        return "redirect:/instituicao/listInstituicoes/";
    }

    @Transactional
    @GetMapping ("/materia/delete/{id}")
    public String deleteMateria(@PathVariable long id){
        Materia materia = materiaRepository.findById(id);
        List<Publicacao> publicacoes = publicacaoRepository.findByMateria(materia);
        publicacoes.forEach(publicacao -> publicacao.setMateria(null));

        try {

                materiaRepository.delete(materia);



        }catch (Exception e){
            System.out.println(e);
        }
        return "redirect:/instituicao/listInstituicoes/";
    }

}
