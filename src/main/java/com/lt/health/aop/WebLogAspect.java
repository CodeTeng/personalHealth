package com.lt.health.aop;

import com.sun.istack.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @description: 日志切面类
 * @author: 狂小腾
 * @date: 2022/3/31 21:02
 */
@Component
@Aspect
public class WebLogAspect {

    private Logger logger = Logger.getLogger(WebLogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 切入点 TestController下的hello函数
     */
    @Pointcut("execution(public * com.lt.health.controller.TestController.hello(..))")
    public void point() {}

    @Before("point()")
    public void doBefore(JoinPoint joinpoint) {
        // 设置初始时间
        startTime.set(System.currentTimeMillis());
        // 接收到请求内容，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL : " + request.getRequestURI());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinpoint.getArgs()));
    }

    @AfterReturning(pointcut = "point()", returning = "obj")
    public void doAfterRunning(Object obj) {
        // 处理完请求 返回内容
        logger.info("RESPONSE : " + obj);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
