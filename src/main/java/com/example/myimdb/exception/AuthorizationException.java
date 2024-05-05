package com.example.myimdb.exception;

public class AuthorizationException extends RuntimeException {

    /**
     * 异常构造方法 在使用时方便传入错误码和信息
     */
    public AuthorizationException(String msg) {
        super(msg);
    }

}
