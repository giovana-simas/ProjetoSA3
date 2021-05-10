package com.sa.controller;

import com.sa.model.MateriaSugerida;
import com.sa.repository.MateriaSugeridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MateriaSugeridaController {

    @Autowired
    MateriaSugeridaRepository materiaSugeridaRepository;

    @PostMapping(path = "/materiaSugerida/save")
    public void save(Model model){



    }

}
