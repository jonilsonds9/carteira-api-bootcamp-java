package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@AllArgsConstructor
public class RelatoriosController {

    private RelatorioService service;

    @GetMapping("/carteira")
    public List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos() {
        return service.relatorioCarteiraDeInvestimentos();
    }

}
