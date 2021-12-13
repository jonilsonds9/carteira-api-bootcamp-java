package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Page<TransacaoDto> listar(Pageable paginacao) {
        Page<Transacao> transacoes = transacaoRepository.findAll(paginacao);
        return transacoes.map(t -> modelMapper.map(t, TransacaoDto.class));
    }

    @Transactional
    public TransacaoDto cadastrar(TransacaoFormDto dto) {
        Long usuarioId = dto.getUsuarioId();
        Usuario usuario = usuarioRepository.getById(usuarioId);

        Transacao transacao = modelMapper.map(dto, Transacao.class);
        transacao.setId(null);
        transacao.setUsuario(usuario);
        transacaoRepository.save(transacao);
        return modelMapper.map(transacao, TransacaoDto.class);
    }
}
