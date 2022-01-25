package com.alura.alurafinancas.repository;

import com.alura.alurafinancas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findByDescricaoAndData(String descricao, LocalDate data);
    List<Receita> findByDescricaoContains(String descricao);

}
