package com.alura.alurafinancas.controller;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.service.ReceitaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    ReceitaServices receitaServices;

    @PostMapping
    public ResponseEntity<ReceitaDTO> cadastrarReceita(@Valid @RequestBody ReceitaDTO receita) {
        ReceitaDTO receitaDTO = receitaServices.cadastrarReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(receitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> buscaReceitaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(receitaServices.buscaReceitaPorId(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> atualizaReceita(@Valid @PathVariable Long id, @RequestBody ReceitaDTO receita) {
        receita.setId(id);
        ReceitaDTO receitaDTO = receitaServices.atualizaReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(receitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluiReceita(@PathVariable Long id) {
        receitaServices.excluiReceita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{ano}/{mes}")
    public ResponseEntity<List<ReceitaDTO>> listarReceitaPorMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok(receitaServices.listarReceitasPorMes(ano, mes));
    }

    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listarReceitas(@RequestParam(required = false) String descricao) {
        return ResponseEntity.ok(receitaServices.listarReceitas(descricao));
    }
}
