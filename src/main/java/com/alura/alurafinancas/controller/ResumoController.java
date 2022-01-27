package com.alura.alurafinancas.controller;

import com.alura.alurafinancas.DTO.ResumoMesDTO;
import com.alura.alurafinancas.service.ResumoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    private ResumoServices resumoServices;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResumoMesDTO> buscaResumoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok(resumoServices.buscaResumoMes(ano, mes));
    }

}
