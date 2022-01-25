package com.alura.alurafinancas.model;

import com.alura.alurafinancas.DTO.DespesaDTO;
import com.alura.alurafinancas.model.enums.Categoria;
import com.alura.alurafinancas.model.enums.StatusPagamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusPagamento pago;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Despesa() {
    }

    public Despesa(Long id, String descricao, BigDecimal valor, LocalDate data, StatusPagamento pago, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.pago = pago;
        this.categoria = categoria;
    }

    public Despesa(DespesaDTO despesaDTO) {
        this.id = despesaDTO.getId();
        this.descricao = despesaDTO.getDescricao();
        this.valor = despesaDTO.getValor();
        this.data = despesaDTO.getData();
        this.pago = despesaDTO.getPago();
        this.categoria = despesaDTO.getCategoria();
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
