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

    private Optional<Despesa> despesaExistePorId(DespesaDTO despesaDTO) {
        return despesaRepository.findById(despesaDTO.getId());
    }

    public DespesaDTO cadastrarDespesa(DespesaDTO despesaDTO) {
        if (despesaExiste(despesaDTO)) {
            Despesa despesa = new Despesa(despesaDTO);
            return new DespesaDTO(despesaRepository.save(despesa));
        } else {
            throw new DataIntegrityViolationException("Despesa já cadastrada!");
        }
    }

    public DespesaDTO buscaDespesaPorId(Long id) {
        Optional<Despesa> despesa = despesaRepository.findById(id);
        if (despesa.isPresent()) {
            return new DespesaDTO(despesa.get());
        }
        throw new IllegalArgumentException("Não foi encontrada nenhuma despesa com id: " + id);
    }

    public DespesaDTO atualizaDespesa(DespesaDTO despesaDTO) {
        if (despesaExistePorId(despesaDTO).isPresent()) {
            if (despesaExiste(despesaDTO)) {
                return new DespesaDTO(despesaRepository.save(new Despesa(despesaDTO)));
            } else {
                throw new IllegalArgumentException("Data ou descrição devem ser diferentes, para realizar a alteração.");
            }
        } else {
            throw new IllegalArgumentException("Não foi encontrada nenhuma despesa com id: " + despesaDTO.getId());
        }
    }
}
