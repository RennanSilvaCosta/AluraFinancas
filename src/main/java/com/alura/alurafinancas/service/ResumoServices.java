package com.alura.alurafinancas.service;

import com.alura.alurafinancas.DTO.ResumoMesDTO;
import com.alura.alurafinancas.repository.DespesaRepository;
import com.alura.alurafinancas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ResumoServices {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public ResumoMesDTO buscaResumoMes(Integer ano, Integer mes) {
        LocalDate dataInicial = LocalDate.of(ano, mes, 1);
        LocalDate dataFinal = LocalDate.of(ano, mes, dataInicial.lengthOfMonth());
        ResumoMesDTO resumo = new ResumoMesDTO();

        Optional<Double> totalReceita = receitaRepository.totalMes(dataInicial, dataFinal);
        Optional<Double> totalDespesa = despesaRepository.totalMes(dataInicial, dataFinal);

        if (totalReceita.isPresent()) {
            resumo.setValorTotalReceita(totalReceita.get());
        } else {
            resumo.setValorTotalReceita(0.0);
        }
        if (totalDespesa.isPresent()) {
            resumo.setValorTotalDespesa(totalDespesa.get());
        } else {
            resumo.setValorTotalDespesa(0.0);
        }

        if (resumo.getValorTotalReceita() == 0 && resumo.getValorTotalDespesa() == 0) {
            throw new DataIntegrityViolationException("Nenhum lançamento encontrado para a data informada!");
        }

        resumo.setSaldoFinal(resumo.getValorTotalReceita() - resumo.getValorTotalDespesa());
        resumo.setGastoCategoria(despesaRepository.totalMesPorCategoria(dataInicial, dataFinal));

        return resumo;
    }
}
