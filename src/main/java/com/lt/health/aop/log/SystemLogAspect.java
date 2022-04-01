package com.lt.health.aop.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lt.health.entity.LogMessage;
import com.lt.health.entity.User;
import com.lt.health.mapper.LogMessageMapper;
import com.lt.health.utils.SecurityUtil;
import com.lt.health.utils.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @description: 定义切面类
 * @author: 狂小腾
 * @date: 2022/4/1 18:39
 */
@Component
@Aspect
public class SystemLogAspect {

    @Autowired
    private WebUtil webUtil;

    @Autowired
    private LogMessageMapper logMessageMapper;

    /**
     * 注解Pointcut切入点
     * 定义出一个或一组方法，当执行这些方法时可产生通知
     * 指向你的切面类方法
     * 由于这里使用了自定义注解所以指向你的自定义注解
     */
    @Pointcut("@annotation(com.lt.health.aop.log.SystemCrmLog)")
    public void crmPoint() {
    }

    @AfterThrowing(value = "crmPoint()", throwing = "exception")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) throws Exception {
        HttpServletRequest request = this.getHttpServletRequest();
        // 获取用户信息
        LogMessage logMessage = new LogMessage();
        String context = getServiceMethodDescription(joinPoint);
        User user = SecurityUtil.getUser();
        String userName = user.getUserName();
        // 用户姓名
        logMessage.setUserName(userName);
        // 日志信息
        logMessage.setContent(userName + context);
        // 设置参数集合
        logMessage.setRemarks(this.getServiceMethodParams(joinPoint));
        // 设置表名
        logMessage.setTableName(Arrays.toString(this.getServiceMethodTableName(joinPoint)));
        // 操作时间
        SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logMessage.setDateTime(sif.format(new Date()));
        // 设置ip地址
        logMessage.setIp(request.getRemoteAddr());
        // 设置请求地址
        logMessage.setRequestUrl(request.getRequestURI());
        // 执行结果
        logMessage.setResult("执行失败");
        // 错误信息
        logMessage.setResultValue(exception.getMessage());
        // 将数据保存数据库中
        logMessageMapper.insert(logMessage);
    }

    @AfterReturning(value = "crmPoint()", returning = "returnValue")
    public void doAfterRunning(JoinPoint joinPoint, Object returnValue) throws Exception {
        HttpServletRequest request = this.getHttpServletRequest();
        // 获取用户信息
        LogMessage logMessage = new LogMessage();
        String context = getServiceMethodDescription(joinPoint);
        User user = SecurityUtil.getUser();
        String userName = user.getUserName();
        // 用户姓名
        logMessage.setUserName(userName);
        // 日志信息
        logMessage.setContent(userName + context);
        // 设置参数集合
        logMessage.setRemarks(this.getServiceMethodParams(joinPoint));
        // 设置表名
        logMessage.setTableName(Arrays.toString(this.getServiceMethodTableName(joinPoint)));
        // 操作时间
        SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logMessage.setDateTime(sif.format(new Date()));
        // 设置ip地址
        logMessage.setIp(request.getRemoteAddr());
        // 设置请求地址
        logMessage.setRequestUrl(request.getRequestURI());
        if (returnValue != null) {
            if (returnValue instanceof List) {
                List ls = (List) returnValue;
                if (ls.size() > 0) {
                    logMessage.setResult("执行成功");
                } else {
                    logMessage.setResult("执行失败");
                }
            } else if (returnValue instanceof Boolean) {
                Boolean flag = (Boolean) returnValue;
                if (flag) {
                    logMessage.setResult("执行成功");
                } else {
                    logMessage.setResult("执行失败");
                }
            } else if (returnValue instanceof Integer) {
                Integer i = (Integer) returnValue;
                if (i > 0) {
                    logMessage.setResult("执行成功");
                } else {
                    logMessage.setResult("执行失败");
                }
            } else {
                logMessage.setResult("执行成功");
            }
        }
        // 将数据保存到数据库
        logMessageMapper.insert(logMessage);
    }

    /**
     * 获取当前的request
     *
     * @return request
     */
    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取自定义注解里的日志描述
     *
     * @param joinPoint jointPoint
     * @return 返回注解里面的日志描述
     */
    private String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        // 类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        // 参数
        Object[] arguments = joinPoint.getArgs();
        // 通过反射获取实例对象
        Class<?> targetClass = Class.forName(targetName);
        // 获取实例对象方法数组
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            // 判断方法名是否一样
            if (method.getName().equals(methodName)) {
                // 对比参数数组的长度
                Class<?>[] classes = method.getParameterTypes();
                if (classes.length == arguments.length) {
                    // 获取注解里面的日志信息
                    description = method.getAnnotation(SystemCrmLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取自定义注解的表名
     *
     * @return 返回注解里的表名字
     */
    private String[] getServiceMethodTableName(JoinPoint joinPoint) throws Exception {
        //类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        //参数
        Object[] arguments = joinPoint.getArgs();
        //通过反射获取示例对象
        Class<?> targetClass = Class.forName(targetName);
        //通过实例对象方法数组
        Method[] methods = targetClass.getMethods();
        //表名
        String[] tableName = null;
        for (Method method : methods) {
            // 判断方法名是不是一样
            if (method.getName().equals(methodName)) {
                // 对比参数数组的长度
                Class[] classes = method.getParameterTypes();
                if (classes.length == arguments.length) {
                    // 获取注解里的表名
                    tableName = method.getAnnotation(SystemCrmLog.class).tableName();
                    break;
                }
            }
        }
        return tableName;
    }

    /**
     * 获取json格式的参数用于存储到数据库中
     *
     * @param joinPoint jointPoint
     */
    private String getServiceMethodParams(JoinPoint joinPoint) throws Exception {
        Object[] arguments = joinPoint.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(arguments);
    }
}
