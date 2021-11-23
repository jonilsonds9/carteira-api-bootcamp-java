package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelatorioService {

    private TransacaoRepository repository;

    public List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos() {
        return repository.relatorioCarteiraDeInvestimentos();
    }
}
