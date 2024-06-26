package com.phoenix.scaffold.exception;

import com.phoenix.scaffold.lang.RespStatus;
import com.phoenix.scaffold.lang.Result;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Null;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Null> doException(Exception ex) {
        log.error("服务出现的异常:{}", ex.getMessage());
        ex.printStackTrace(System.err);
        return Result.fail(RespStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Null> doHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Http 消息不可读异常:{}", ex.getMessage());
        return Result.fail("Http 消息不可读异常");
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result<Null> doIllegalStateException(IllegalStateException ex) {
        log.error("运行服务出现的IllegalStateException异常:{}", ex.getMessage());
        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Null> doIllegalArgumentException(IllegalArgumentException ex) {
        log.error("运行服务出现的IllegalArgumentException异常:{}", ex.getMessage());
        return Result.fail(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<Null> doMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("参数校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(";");
        }
        return Result.fail(sb.toString());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<Null> doConstraintViolationException(ConstraintViolationException ex) {
        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<BusinessException> processBusinessException(BusinessException businessException) {
        log.error(businessException.getLocalizedMessage());
        return Result.fail(null, businessException.getLocalizedMessage() == null ? RespStatus.INTERNAL_SERVER_ERROR.getMessage() : businessException.getLocalizedMessage());
    }
}
