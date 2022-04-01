package com.lt.health.aop.log;

import java.lang.annotation.*;

/**
 * @description: AOP日志记录 自定义注解类
 * @author: 狂小腾
 * @date: 2022/4/1 18:32
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemCrmLog {

    /**
     * 日志描述
     * 对于什么表格进行了什么操作
     */
    String description() default "";

    /**
     * 操作了的表名---可以有多个
     */
    String[] tableName() default "";
}
