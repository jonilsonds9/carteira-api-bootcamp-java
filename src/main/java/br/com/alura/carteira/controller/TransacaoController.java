package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.service.TransacaoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService service;

    @GetMapping
    public Page<TransacaoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.listar(paginacao);
    }

    @PostMapping
    public ResponseEntity<TransacaoDto> cadastrar(@RequestBody @Valid TransacaoFormDto dto
                                                  , UriComponentsBuilder uriBuilder) {
        TransacaoDto transacaoDto = service.cadastrar(dto);

        URI uri = uriBuilder
                .path("/transacoes/{id}")
                .buildAndExpand(transacaoDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(transacaoDto);
    }
}
