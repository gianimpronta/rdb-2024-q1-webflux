package dev.gianimpronta.crebito.advisors;

import dev.gianimpronta.crebito.exception.SaldoInconsistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SaldoInconsistenteAdvice {

    @ExceptionHandler(SaldoInconsistenteException.class)
    ResponseEntity<String> saldoInconsistenteHandler(SaldoInconsistenteException ex) {
        return new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
