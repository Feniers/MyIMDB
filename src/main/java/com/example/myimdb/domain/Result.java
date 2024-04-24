package com.example.myimdb.domain;

import com.example.myimdb.config.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回类
 */
@Data
public class Result {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = "";
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = "";
    }

    public Result(ResultStatus status, Object data) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public static Result ok(Object data) {
        return new Result(ResultStatus.SUCCESS, data);
    }

    public static Result ok() {
        return new Result(ResultStatus.SUCCESS);
    }

    public static Result error(ResultStatus error) {
        return new Result(error);
    }

    public static Result error(int code,String message) {
        return new Result(code,message);
    }

}
