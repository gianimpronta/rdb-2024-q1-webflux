package dev.gianimpronta.crebito.advisors;

import dev.gianimpronta.crebito.exception.ClienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ClienteNotFoundAdvice {
    private static final String ERROR_MESSAGE = "";

    @ResponseBody
    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> clienteNotFoundHandler(ClienteNotFoundException e) {
        return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.NOT_FOUND);
    }
}
