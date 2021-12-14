package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private TransacaoService service;

    private TransacaoFormDto criarTransacaoForm() {
        TransacaoFormDto formDto = new TransacaoFormDto(
                "ITSA4",
                new BigDecimal("10.45"),
                LocalDate.now(),
                120,
                TipoTransacao.COMPRA,
                1L
        );
        return formDto;
    }

    @Test
    void deveriaCadastrarUmaTransacao() {
        TransacaoFormDto formDto = criarTransacaoForm();

        TransacaoDto dto = service.cadastrar(formDto);

        verify(transacaoRepository).save(any());

        assertEquals(formDto.getTicker(), dto.getTicker());
        assertEquals(formDto.getPreco(), dto.getPreco());
        assertEquals(formDto.getQuantidade(), dto.getQuantidade());
        assertEquals(formDto.getTipo(), dto.getTipo());
    }

    @Test
    void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {
        TransacaoFormDto formDto = criarTransacaoForm();

        when(usuarioRepository.getById(1L)).thenThrow(EntityNotFoundException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(formDto);
        });
    }

}