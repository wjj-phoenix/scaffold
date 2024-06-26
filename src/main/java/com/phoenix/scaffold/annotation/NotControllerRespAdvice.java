package com.phoenix.scaffold.annotation;

import java.lang.annotation.*;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerRespAdvice {
}
