package com.phoenix.scaffold.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.phoenix.scaffold.entity.SysOperationLog;
import com.phoenix.scaffold.service.ISysOperationLogService;
import com.phoenix.scaffold.utils.IPUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wjj-phoenix
 * @since 2024-06-25
 */
@Log4j2
@Aspect
@Component
public class ControllerLoggingAspect {
    @Resource
    private ISysOperationLogService operationLogService;

    /**
     * 拦截所有controller包下的方法
     */
    @Pointcut("execution(* com.phoenix.scaffold.controller..*.*(..))")
    private void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取本次接口的唯一码
        String token = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        MDC.put("requestId", token);

        // 获取HttpServletRequest
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) attributes;
        HttpServletRequest request = Objects.requireNonNull(sra).getRequest();
        String clientAddr = IPUtil.address(request);
        MDC.put("clientAddr", clientAddr);

        // 获取请求相关信息
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        StringBuilder params = new StringBuilder(request.getQueryString() == null ? "" : request.getQueryString());
        if (StringUtils.isEmpty(params.toString()) && StringUtils.equals("POST", method)) {
            if (Objects.nonNull(joinPoint.getArgs())) {
                for (Object arg : joinPoint.getArgs()) {
                    params.append(arg);
                }
            }
        }
        // 获取调用方法相信
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        ParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
        String[] paramNames = discoverer.getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        SysOperationLog operationLog = SysOperationLog.builder()
                .uuid(token)
                .operation("")
                .uri(uri)
                .reqMethod(method)
                .methodName(methodName)
                .params(this.handleParams(params, args, Arrays.asList(Objects.requireNonNull(paramNames))))
                .clientAddr(clientAddr)
                .remoteAddr(request.getRemoteAddr())
                .operator(1L)
                .operatedTime(LocalDateTime.now())
                .build();
        // result的值就是被拦截方法的返回值
        try {
            // proceed方法是调用实际所拦截的controller中的方法，这里的result为调用方法后的返回值
            Object result = joinPoint.proceed();
            long timestamp = System.currentTimeMillis() - startTime;
            // 定义请求结束时的返回数据，包括调用时间、返回值结果等
            log.info("@http请求: {}#{}(), URI: {}, method: {}, URL: {}, params: {}, time: {}ms ", className, methodName, uri, method, url, params.toString(), timestamp);
            operationLog.setResult(1);
            operationLog.setRespTime(timestamp);
            return result;
        } catch (Exception e) {
            long timestamp = System.currentTimeMillis() - startTime;
            operationLog.setResult(0);
            operationLog.setReason(e.getMessage());
            operationLog.setRespTime(timestamp);
            log.error("@http请求出错, {}#{}(), URI: {}, method: {}, URL: {}, params: {}, time: {}ms", className, methodName, uri, method, url, params.toString(), timestamp, e);
            throw e;
        } finally {
            log.info("{}", operationLog);
            if (!StrUtil.equalsIgnoreCase(method, "GET")) {
                operationLogService.addSysOperationLog(operationLog);
            }
            MDC.remove("requestId");
            MDC.remove("clientAddr");
        }
    }

    /**
     * 参数打印合理化
     *
     * @param params     参数字符串
     * @param args       参数列表
     * @param paramNames 参数名
     * @return 结构化参数
     */
    private String handleParams(StringBuilder params, Object[] args, List<Object> paramNames) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set<?> set = ((Map<?, ?>) args[i]).keySet();
                List<Object> list = new ArrayList<>();
                List<Object> paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map<?, ?>) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的toString方法
                        params.append(" ").append(paramNames.get(i)).append(": ").append(JSONUtil.toJsonStr(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(JSONUtil.toJsonStr(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile file) {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return String.valueOf(params);
    }
}
