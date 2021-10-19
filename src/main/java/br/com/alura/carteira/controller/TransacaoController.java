package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private List<Transacao> transacoes = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<TransacaoDto> listar() {
        return transacoes.stream()
                .map(t -> modelMapper.map(t, TransacaoDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid TransacaoFormDto dto) {
        Transacao transacao = modelMapper.map(dto, Transacao.class);
        transacoes.add(transacao);
    }
}
