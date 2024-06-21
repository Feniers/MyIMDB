package com.example.myimdb.exception;

import com.example.myimdb.domain.Result;
import com.example.myimdb.domain.ResultStatus;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("服务器异常: " + e.getMessage(), e);
        return new ResponseEntity<>(Result.error(-1004, "服务器异常: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Result> handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Result.error(e.resultStatus), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Result.error(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Result.error(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
