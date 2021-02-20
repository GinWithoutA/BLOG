package com.patrickstar.blog.aspect;/*
 * @PackageName: com.patrickstar.blog.aspect
 * @ClassName: LogAspect
 * @Description:日志处理
 * @Author: PatrickStaR
 * @Date: 10/30/2019 12:54 PM
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private class RequestInfo {
        //请求url
        private String url;
        //访问者ip
        private String ip;
        //调用的方法
        private String classMethod;
        //参数args
        private Object[] args;

        public RequestInfo(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getClassMethod() {
            return classMethod;
        }

        public void setClassMethod(String classMethod) {
            this.classMethod = classMethod;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }


        @Override
        public String toString() {
            return "RequestInfo{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.patrickstar.blog.controller.*.*(..))")
    public void log(){

    }
    /**
     * 记录请求的参数
    **/
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestInfo requestInfo = new RequestInfo(url, ip, classMethod, args);
        logger.info("Request : {}", requestInfo);
    }

    @After("log()")
    public void doAfter(){
        logger.info("-------------doAfter--------------");
    }

    /**
     * 记录返回的值
    **/
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturn(Object object){
        logger.info("-------------AfterReturning--------------");
        logger.info("Result : {}", object);
    }
}
