package com.alura.alurafinancas.controller;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.service.ReceitaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaController {

    @Autowired
    ReceitaServices receitaServices;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ReceitaDTO> cadastrarReceita(@RequestBody ReceitaDTO receita) {
        ReceitaDTO receitaDTO = receitaServices.cadastrarReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(receitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaDTO);
    }

}
