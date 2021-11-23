package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<TransacaoDto> listar() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        return transacoes.stream()
                .map(t -> modelMapper.map(t, TransacaoDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void cadastrar(TransacaoFormDto dto) {
        Transacao transacao = modelMapper.map(dto, Transacao.class);
        transacao.setId(null);
        transacaoRepository.save(transacao);
    }
}
