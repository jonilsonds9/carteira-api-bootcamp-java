package br.com.alura.carteira.dto;

import br.com.alura.carteira.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransacaoFormDto {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 6)
    private String ticker;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal preco;

    @PastOrPresent
    private LocalDate data;

    @NotNull
    private int quantidade;

    @NotNull
    private TipoTransacao tipo;

}
