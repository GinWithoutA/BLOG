package com.patrickstar.blog.interceptor;/*
 * @PackageName: com.patrickstar.blog.interceptor
 * @ClassName: LoginInterceptor
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/31/2019 12:22 PM
 */

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String targetAttribute = "loginUser";
        if(request.getSession().getAttribute(targetAttribute) == null){
            response.sendRedirect("/admin");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
