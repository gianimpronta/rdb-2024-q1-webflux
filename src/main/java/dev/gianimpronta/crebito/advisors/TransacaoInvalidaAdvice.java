package dev.gianimpronta.crebito.advisors;

import dev.gianimpronta.crebito.exception.TransacaoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class TransacaoInvalidaAdvice {

    @ExceptionHandler(TransacaoInvalidaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void badRequestHandler(TransacaoInvalidaException ex) {
        // validator
    }
}
