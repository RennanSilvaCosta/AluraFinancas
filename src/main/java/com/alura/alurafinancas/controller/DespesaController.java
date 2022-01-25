package com.alura.alurafinancas.controller;

import com.alura.alurafinancas.DTO.DespesaDTO;
import com.alura.alurafinancas.service.DespesaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    DespesaServices despesaServices;

    @PostMapping
    public ResponseEntity<DespesaDTO> cadastrarDespesa(@Valid @RequestBody DespesaDTO despesa) {
        DespesaDTO depesaDTO = despesaServices.cadastrarDespesa(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(depesaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(depesaDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaDTO> buscaDespesaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(despesaServices.buscaDespesaPorId(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DespesaDTO> atualizaDespesa(@Valid @PathVariable Long id, @RequestBody DespesaDTO despesa) {
        despesa.setId(id);
        DespesaDTO despesaDTO = despesaServices.atualizaDespesa(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(despesaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(despesaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluiDespesa(@PathVariable Long id) {
        despesaServices.excluiDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> listarDespesas(@RequestParam(required = false) String descricao) {
        return ResponseEntity.ok(despesaServices.listarDespesas(descricao));
    }

    @GetMapping(value = "/{ano}/{mes}")
    public ResponseEntity<List<DespesaDTO>> listarReceitaPorMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok(despesaServices.listarDespesasPorMes(ano, mes));
    }

}
