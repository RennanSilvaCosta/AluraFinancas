package com.alura.alurafinancas.controller;

import com.alura.alurafinancas.DTO.DespesaDTO;
import com.alura.alurafinancas.service.DespesaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    DespesaServices despesaServices;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DespesaDTO> cadastrarDespesa(@RequestBody DespesaDTO despesa) {
        DespesaDTO depesaDTO = despesaServices.cadastrarDespesa(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(depesaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(depesaDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<DespesaDTO> buscaDespesaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(despesaServices.buscaDespesaPorId(id));
    }

}
