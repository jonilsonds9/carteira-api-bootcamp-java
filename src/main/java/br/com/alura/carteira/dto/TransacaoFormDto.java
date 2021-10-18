package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransacaoFormDto {

    private String ticker;
    private BigDecimal preco;
    private int quantidade;
    private LocalDate data;
    private TipoTransacao tipo;

}
