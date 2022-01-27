package com.alura.alurafinancas.DTO;

import com.alura.alurafinancas.model.enums.Categoria;

import java.math.BigDecimal;

public class GastoCategoriaDTO {

    private Categoria categoria;
    private BigDecimal valor;

    public GastoCategoriaDTO(Categoria categoria, BigDecimal valorTotal) {
        this.categoria = categoria;
        this.valor = valorTotal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
