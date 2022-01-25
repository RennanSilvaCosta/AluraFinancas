package com.alura.alurafinancas.model;

import com.alura.alurafinancas.DTO.ReceitaDTO;
import com.alura.alurafinancas.model.enums.StatusPagamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusPagamento pago;

    public Receita() {
    }

    public Receita(Long id, String descricao, BigDecimal valor, LocalDate data, StatusPagamento pago) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.pago = pago;
    }

    public Receita(ReceitaDTO receitaDTO) {
        this.id = receitaDTO.getId();
        this.descricao = receitaDTO.getDescricao();
        this.valor = receitaDTO.getValor();
        this.data = receitaDTO.getData();
        this.pago = receitaDTO.getPago();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StatusPagamento getPago() {
        return pago;
    }

    public void setPago(StatusPagamento pago) {
        this.pago = pago;
    }
}
