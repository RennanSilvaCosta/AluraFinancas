package com.alura.alurafinancas.DTO;

import java.util.List;

public class ResumoMesDTO {

    private Double valorTotalReceita = 0.0;
    private Double valorTotalDespesa = 0.0;
    private Double saldoFinal = 0.0;
    private List<GastoCategoriaDTO> gastoCategoria;

    public ResumoMesDTO() {
    }

    public Double getValorTotalReceita() {
        return valorTotalReceita;
    }

    public void setValorTotalReceita(Double valorTotalReceita) {
        this.valorTotalReceita = valorTotalReceita;
    }

    public Double getValorTotalDespesa() {
        return valorTotalDespesa;
    }

    public void setValorTotalDespesa(Double valorTotalDespesa) {
        this.valorTotalDespesa = valorTotalDespesa;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public List<GastoCategoriaDTO> getGastoCategoria() {
        return gastoCategoria;
    }

    public void setGastoCategoria(List<GastoCategoriaDTO> gastoCategoria) {
        this.gastoCategoria = gastoCategoria;
    }
}
