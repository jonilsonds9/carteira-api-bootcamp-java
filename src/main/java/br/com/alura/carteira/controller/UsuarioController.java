package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.service.UsuarioService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public Page<UsuarioDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.listar(paginacao);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto dto,
                                                UriComponentsBuilder uriBuilder) {
        UsuarioDto usuarioDto = service.cadastrar(dto);

        URI uri = uriBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuarioDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }

}
