package com.alura.alurafinancas.model;

import com.alura.alurafinancas.DTO.DespesaDTO;
import com.alura.alurafinancas.model.enums.StatusPagamento;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double valor;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusPagamento pago;

    public Despesa() {
    }

    public Despesa(Long id, String descricao, Double valor, LocalDate data, StatusPagamento pago) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.pago = pago;
    }

    public Despesa(DespesaDTO despesaDTO) {
        this.id = despesaDTO.getId();
        this.descricao = despesaDTO.getDescricao();
        this.valor = despesaDTO.getValor();
        this.data = despesaDTO.getData();
        this.pago = despesaDTO.getPago();
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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
