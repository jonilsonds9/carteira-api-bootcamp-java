package br.com.alura.carteira.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratamenteDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<Erro400Dto> tratarErro400(MethodArgumentNotValidException ex) {
        return ex
                .getFieldErrors()
                .stream()
                .map(e -> new Erro400Dto(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Erro500Dto tratarErro500(Exception ex, HttpServletRequest req) {
        return new Erro500Dto(
                LocalDateTime.now(),
                ex.getClass().toString(),
                ex.getMessage(),
                req.getRequestURI());
    }
}
