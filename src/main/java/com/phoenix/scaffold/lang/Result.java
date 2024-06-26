package com.phoenix.scaffold.lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 响应时间
     */
    private long timestamp;

    /**
     * 状态码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应体内容
     */
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder().data(data)
                .message(RespStatus.SUCCESS.getMessage())
                .code(RespStatus.SUCCESS.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> Result<T> fail(RespStatus status) {
        return Result.<T>builder().data(null)
                .message(status.getMessage())
                .code(status.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> Result<T> fail(String message) {
        return fail(null, message);
    }

    public static <T> Result<T> fail(T data, String message) {
        return Result.<T>builder().data(data)
                .message(message)
                .code(RespStatus.CUSTOM_EXCEPTION.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}