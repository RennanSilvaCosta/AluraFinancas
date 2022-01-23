package com.alura.alurafinancas.service;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.model.Receita;
import com.alura.alurafinancas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

    public ReceitaDTO cadastrarReceita(ReceitaDTO receitaDTO) throws DataIntegrityViolationException {
        if (receitaExiste(receitaDTO)) {
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

    public ReceitaDTO atualizaReceita(ReceitaDTO receitaDTO) {
        if (receitaExiste(receitaDTO)) {
            return new ReceitaDTO(receitaRepository.save(new Receita(receitaDTO)));
        } else {
            throw new IllegalArgumentException("Data ou descrição devem ser diferentes, para realizar a alteração.");
        }
    }

    public void excluiReceita(Long id) {
        try {
            receitaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não foi possivel deletar a receita informado");
        }
    }

    public List<ReceitaDTO> listarReceitas() {
        return receitaRepository.findAll().stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
    }
}
