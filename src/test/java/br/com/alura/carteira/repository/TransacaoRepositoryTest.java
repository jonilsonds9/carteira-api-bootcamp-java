package br.com.alura.carteira.repository;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class TransacaoRepositoryTest {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void deveriaRetornarRelatorioDeCarteiraDeInvestimentos() {
        Usuario usuario = new Usuario("Rafaela", "rafa@email.com", "123456");
        em.persist(usuario);

        Transacao t1 = new Transacao("ITSA4",
                LocalDate.now(),
                new BigDecimal("10.00"),
                50,
                TipoTransacao.COMPRA,
                usuario);
        em.persist(t1);

        Transacao t2 = new Transacao("BBSE3",
                LocalDate.now(),
                new BigDecimal("22.80"),
                80,
                TipoTransacao.COMPRA,
                usuario);
        em.persist(t2);

        Transacao t3 = new Transacao("EGIE3",
                LocalDate.now(),
                new BigDecimal("40.00"),
                25,
                TipoTransacao.COMPRA,
                usuario);
        em.persist(t3);

        Transacao t4 = new Transacao("ITSA4",
                LocalDate.now(),
                new BigDecimal("11.20"),
                40,
                TipoTransacao.COMPRA,
                usuario);
        em.persist(t4);

        Transacao t5 = new Transacao("SAPR4",
                LocalDate.now(),
                new BigDecimal("04.02"),
                120,
                TipoTransacao.COMPRA,
                usuario);
        em.persist(t5);

        List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();
        Assertions.assertThat(relatorio)
                .hasSize(4)
                .extracting(ItemCarteiraDto::getTicker, ItemCarteiraDto::getQuantidade, ItemCarteiraDto::getPercentual)
                .containsExactlyInAnyOrder(
                        Assertions.tuple("ITSA4", 90L, 0.285714),
                        Assertions.tuple("BBSE3", 80L, 0.253968),
                        Assertions.tuple("EGIE3", 25L, 0.079365),
                        Assertions.tuple("SAPR4", 120L, 0.380952)
                );
    }
}