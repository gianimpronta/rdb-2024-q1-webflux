package dev.gianimpronta.crebito.advisors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ValidationAdvice {
    private static final String ERROR_MESSAGE = "";

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> validationExceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
