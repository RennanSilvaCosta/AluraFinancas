package com.alura.alurafinancas.repository;

import com.alura.alurafinancas.DTO.GastoCategoriaDTO;
import com.alura.alurafinancas.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Optional<Despesa> findByDescricaoAndData(String descricao, LocalDate data);

    List<Despesa> findByDescricaoContains(String descricao);

    List<Despesa> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);

    @Query(value = "SELECT sum(valor) FROM despesa WHERE despesa.data BETWEEN :dataInicial AND :dataFinal", nativeQuery = true)
    Optional<Double> totalMes(LocalDate dataInicial, LocalDate dataFinal);

    @Query(value = "SELECT new com.alura.alurafinancas.DTO.GastoCategoriaDTO(d.categoria, sum(d.valor))" +
            " FROM Despesa d where d.data between :dataInicial and :dataFinal group by d.categoria")
    List<GastoCategoriaDTO> totalMesPorCategoria(LocalDate dataInicial, LocalDate dataFinal);

}
