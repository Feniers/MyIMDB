package com.example.myimdb.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 统一返回类
 */

@Schema(name = "Result", description = "统一返回类")
@Data
public class Result {
    /**
     * 返回码
     */
    @Schema(description = "返回码", example = "200")
    private int code;

    /**
     * 返回信息
     */
    @Schema(description = "返回信息", example = "success")
    private String message;

    /**
     * 数据
     */
    @Schema(description = "数据", example = "{}")
    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;  // 初始化为 null
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = null;  // 初始化为 null
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

    public static Result error(int code, String message) {
        return new Result(code, message);
    }
}
