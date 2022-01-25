package com.alura.alurafinancas.DTO;

import com.alura.alurafinancas.model.Despesa;
import com.alura.alurafinancas.model.enums.Categoria;
import com.alura.alurafinancas.model.enums.StatusPagamento;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaDTO {

    private Long id;

    @NotNull(message = "A descrição não pode ser nula.")
    @NotEmpty(message = "A descrição é obrigatoria.")
    private String descricao;

    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor deve ser maior do que ZERO.")
    private BigDecimal valor;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    private StatusPagamento pago;

    private Categoria categoria = Categoria.OUTRAS;

    public DespesaDTO() {
    }

    public DespesaDTO(Despesa despesa) {
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
        this.data = despesa.getData();
        this.pago = despesa.getPago();
        this.categoria = despesa.getCategoria();
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
