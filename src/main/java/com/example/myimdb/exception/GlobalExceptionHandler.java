package com.example.myimdb.exception;

import com.example.myimdb.domain.Result;
import com.example.myimdb.domain.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 处理所有不可预见的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("服务器异常: " + e.getMessage(), e);
        return new ResponseEntity<>(Result.error( -1004,"服务器异常: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 权限不足异常
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Result> handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Result.error(e.resultStatus), HttpStatus.FORBIDDEN);
    }

}

