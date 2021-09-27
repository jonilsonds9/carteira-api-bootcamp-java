package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoFormDto {

    private String ticker;
    private BigDecimal preco;
    private int quantidade;
    private LocalDate data;
    private TipoTransacao tipo;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}
