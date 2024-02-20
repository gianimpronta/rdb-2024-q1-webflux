package dev.gianimpronta.crebito.advisors;

import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class ValidationAdvice {
    @ResponseBody
    @ExceptionHandler({ MethodArgumentNotValidException.class, DecodingException.class, WebExchangeBindException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void validationExceptionHandler(MethodArgumentNotValidException e) {
        // exception validator
    }
}
