package dev.gianimpronta.crebito.advisors;

import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class ValidationAdvice {
    @ExceptionHandler({ MethodArgumentNotValidException.class, DecodingException.class, WebExchangeBindException.class, HttpMessageNotReadableException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void validationExceptionHandler() {
        // exception validator
    }
}
