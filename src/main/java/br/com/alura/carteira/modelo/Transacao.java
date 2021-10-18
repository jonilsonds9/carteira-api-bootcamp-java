package br.com.alura.carteira.modelo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = {"data", "quantidade", "tipo"})
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    private String ticker;
    private BigDecimal preco;
    private int quantidade;
    private LocalDate data;
    private TipoTransacao tipo;

}
