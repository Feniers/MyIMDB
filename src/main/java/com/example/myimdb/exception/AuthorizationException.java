package com.example.myimdb.exception;

import com.example.myimdb.domain.ResultStatus;

public class AuthorizationException extends RuntimeException {

    /**
     * 异常构造方法 在使用时方便传入错误码和信息
     */
    ResultStatus resultStatus;

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.resultStatus = resultStatus;
    }
}
