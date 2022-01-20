package com.alura.alurafinancas.controller;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.service.ReceitaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ReceitaDTO> getReceitaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(receitaServices.getReceitaById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ReceitaDTO> atualizaReceita(@PathVariable Long id, @RequestBody ReceitaDTO receita) {
        receita.setId(id);
        ReceitaDTO receitaDTO = receitaServices.atualizaReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(receitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> excluiReceita(@PathVariable Long id) {
        receitaServices.excluiReceita(id);
        return ResponseEntity.noContent().build();
    }
}
