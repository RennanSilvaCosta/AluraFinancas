package com.alura.alurafinancas.service;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.model.Receita;
import com.alura.alurafinancas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceitaServices {

    @Autowired
    ReceitaRepository receitaRepository;

    public ReceitaDTO cadastrarReceita(ReceitaDTO receitaDTO) throws DataIntegrityViolationException {
        if (validaReceita(receitaDTO)) {
            Receita receita = new Receita(receitaDTO);
            return new ReceitaDTO(receitaRepository.save(receita));
        } else {
            throw new DataIntegrityViolationException("Receita já cadastrada!");
        }
    }

    public ReceitaDTO getReceitaById(Long id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()) {
            return new ReceitaDTO(receita.get());
        }
        throw new IllegalArgumentException("Não foi encontrada nenhuma receita com id: " + id);
    }

    private Boolean validaReceita(ReceitaDTO receitaDTO) {
        Optional<Receita> receita = receitaRepository.findByDescricaoAndData(receitaDTO.getDescricao(), receitaDTO.getData());
        return receita.isEmpty();
    }
}
