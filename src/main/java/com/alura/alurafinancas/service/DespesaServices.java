package com.alura.alurafinancas.service;

import com.alura.alurafinancas.DTO.DespesaDTO;
import com.alura.alurafinancas.model.Despesa;
import com.alura.alurafinancas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DespesaServices {

    @Autowired
    DespesaRepository despesaRepository;

    private Boolean despesaExiste(DespesaDTO despesaDTO) {
        Optional<Despesa> despesa = despesaRepository.findByDescricaoAndData(despesaDTO.getDescricao(), despesaDTO.getData());
        return despesa.isEmpty();
    }

    public DespesaDTO cadastrarDespesa(DespesaDTO despesaDTO) {
        if (despesaExiste(despesaDTO)) {
            Despesa despesa = new Despesa(despesaDTO);
            return new DespesaDTO(despesaRepository.save(despesa));
        } else {
            throw new DataIntegrityViolationException("Despesa já cadastrada!");
        }
    }

}
