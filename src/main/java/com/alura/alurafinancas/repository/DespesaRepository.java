package com.alura.alurafinancas.repository;

import com.alura.alurafinancas.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Optional<Despesa> findByDescricaoAndData(String descricao, LocalDate data);
    List<Despesa> findByDescricaoContains(String descricao);
    List<Despesa> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
