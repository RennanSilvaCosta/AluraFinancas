package com.alura.alurafinancas.DTO;

import com.alura.alurafinancas.model.Receita;
import com.alura.alurafinancas.model.enums.StatusPagamento;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class ReceitaDTO {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    private Double valor;

    @NotNull
    private LocalDate data;

    @NotNull
    private StatusPagamento pago;

    public ReceitaDTO() {
    }

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.data = receita.getData();
        this.pago = receita.getPago();
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
