package com.phoenix.scaffold.resp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.scaffold.lang.Result;
import jakarta.annotation.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@RestControllerAdvice(basePackages = "com.phoenix.scaffold.controller")
public class IResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, @Nullable MethodParameter parameter, @Nullable MediaType type, @Nullable Class<? extends HttpMessageConverter<?>> converterType, @Nullable ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        // 如果返回值是String类型，那就手动把Result对象转换成JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(body);
    }

    @Override
    public boolean supports(@Nullable MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
}
