package dev.gianimpronta.crebito.advisors;

import dev.gianimpronta.crebito.exception.TransacaoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class TransacaoInvalidaAdvice {

    @ResponseBody
    @ExceptionHandler(TransacaoInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> badRequestHandler(TransacaoInvalidaException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
