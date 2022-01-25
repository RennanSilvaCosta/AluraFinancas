package com.alura.alurafinancas.service;

import com.alura.alurafinancas.DTO.DespesaDTO;
import com.alura.alurafinancas.model.Despesa;
import com.alura.alurafinancas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaServices {

    @Autowired
    DespesaRepository despesaRepository;

    private Boolean despesaExiste(DespesaDTO despesaDTO) {
        Optional<Despesa> despesa = despesaRepository.findByDescricaoAndData(despesaDTO.getDescricao(), despesaDTO.getData());
        return despesa.isEmpty();
    }

    private Optional<Despesa> despesaExistePorId(Long id) {
        return despesaRepository.findById(id);
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
        if (despesaExistePorId(despesaDTO.getId()).isPresent()) {
            if (despesaExiste(despesaDTO)) {
                return new DespesaDTO(despesaRepository.save(new Despesa(despesaDTO)));
            } else {
                throw new IllegalArgumentException("Data ou descrição devem ser diferentes, para realizar a alteração.");
            }
        } else {
            throw new IllegalArgumentException("Não foi encontrada nenhuma despesa com id: " + despesaDTO.getId());
        }
    }

    public void excluiDespesa(Long id) {
        if (despesaExistePorId(id).isPresent()) {
            despesaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Não foi encontrada nenhuma despesa com id: " + id);
        }
    }

    public List<DespesaDTO> listarDespesas(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            return despesaRepository.findAll().stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
        } else {
            return despesaRepository.findByDescricaoContains(descricao).stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
        }
    }

    public List<DespesaDTO> listarDespesasPorMes(Integer ano, Integer mes) {
        LocalDate dataInicial = LocalDate.of(ano, mes, 1);
        LocalDate dataFinal = LocalDate.of(ano, mes, dataInicial.lengthOfMonth());
        return despesaRepository.findByDataBetween(dataInicial, dataFinal).stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
    }
}
