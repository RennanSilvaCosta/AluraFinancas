package com.alura.alurafinancas.service;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.model.Receita;
import com.alura.alurafinancas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaServices {

    @Autowired
    ReceitaRepository receitaRepository;

    private Boolean receitaExiste(ReceitaDTO receitaDTO) {
        Optional<Receita> receita = receitaRepository.findByDescricaoAndData(receitaDTO.getDescricao(), receitaDTO.getData());
        return receita.isEmpty();
    }

    private Optional<Receita> receitaExistePorId(Long id) {
        return receitaRepository.findById(id);
    }

    public ReceitaDTO cadastrarReceita(ReceitaDTO receitaDTO) throws DataIntegrityViolationException {
        if (receitaExiste(receitaDTO)) {
            Receita receita = new Receita(receitaDTO);
            return new ReceitaDTO(receitaRepository.save(receita));
        } else {
            throw new DataIntegrityViolationException("Receita já cadastrada!");
        }
    }

    public ReceitaDTO buscaReceitaPorId(Long id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()) {
            return new ReceitaDTO(receita.get());
        }
        throw new IllegalArgumentException("Não foi encontrada nenhuma receita com id: " + id);
    }

    public ReceitaDTO atualizaReceita(ReceitaDTO receitaDTO) {
        if (receitaExistePorId(receitaDTO.getId()).isPresent()) {
            if (receitaExiste(receitaDTO)) {
                return new ReceitaDTO(receitaRepository.save(new Receita(receitaDTO)));
            } else {
                throw new IllegalArgumentException("Data ou descrição devem ser diferentes, para realizar a alteração.");
            }
        } else {
            throw new IllegalArgumentException("Não foi encontrada nenhuma receita com id: " + receitaDTO.getId());
        }
    }

    public void excluiReceita(Long id) {
        if (receitaExistePorId(id).isPresent()) {
            receitaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Não foi encontrada nenhuma receita com id: " + id);
        }
    }

    public List<ReceitaDTO> listarReceitas(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            return receitaRepository.findAll().stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
        } else {
            return receitaRepository.findByDescricaoContains(descricao).stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
        }
    }

    public List<ReceitaDTO> listarReceitasPorMes(Integer ano, Integer mes) {
        LocalDate dataInicial = LocalDate.of(ano, mes, 1);
        LocalDate dataFinal = LocalDate.of(ano, mes, dataInicial.lengthOfMonth());
        return receitaRepository.findByDataBetween(dataInicial, dataFinal).stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
    }
}
