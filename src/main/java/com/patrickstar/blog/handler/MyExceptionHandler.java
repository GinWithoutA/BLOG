package com.patrickstar.blog.handler;/*
 * @PackageName: com.patrickstar.blog.interceptor
 * @ClassName: ExceptionInterceptor
 * @Description:异常处理
 * @Author: PatrickStaR
 * @Date: 10/30/2019 11:15 AM
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 拿到访问的对象
    **/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception{
        //记录异常信息
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);
        //有指定异常状态的就不处理直接返回给spring
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("/error/error");
        return modelAndView;
    }
}
