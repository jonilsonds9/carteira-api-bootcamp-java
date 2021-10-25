package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private List<Transacao> transacoes = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<TransacaoDto> listar() {
        return transacoes.stream()
                .map(t -> modelMapper.map(t, TransacaoDto.class))
                .collect(Collectors.toList());
    }

    public void cadastrar(TransacaoFormDto dto) {
        Transacao transacao = modelMapper.map(dto, Transacao.class);
        transacoes.add(transacao);
    }
}
