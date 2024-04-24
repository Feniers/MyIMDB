package com.example.myimdb.exception;

import com.example.myimdb.domain.Result;
import com.example.myimdb.config.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有不可预见的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        // 记录异常信息，例如使用日志系统记录下详细错误
        e.printStackTrace(); // 仅在开发阶段使用，生产环境应使用日志记录
        return new ResponseEntity<>(Result.error( -1004,"Internal Server Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    // 特定类型的异常可以单独处理
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Result> handleIllegalArgumentException(IllegalArgumentException e) {
//        return new ResponseEntity<>(Result.error(ResultStatus.BAD_REQUEST, "Bad Request: " + e.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    // 处理自定义的异常，例如用户认证失败异常
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ResponseEntity<Result> handleAuthenticationException(AuthenticationException e) {
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(Result.error(ResultStatus.UNAUTHORIZED, "Unauthorized: " + e.getMessage()));
//    }
}

