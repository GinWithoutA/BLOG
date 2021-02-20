package com.patrickstar.blog;/*
 * @PackageName: com.patrickstar.blog
 * @ClassName: WebConfig
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/31/2019 12:30 PM
 */

import com.patrickstar.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 拦截器注册
     *
     * @author PatrickStaR
     * @date 12:36 PM 10/31/2019
    **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //拦截哪些内容：所有admin,category,blog,tag下面的控制器
                .addPathPatterns("/admin/**")
                .addPathPatterns("/blog/**")
                .addPathPatterns("/category/**")
                .addPathPatterns("/tag/**")
                //排除首页
                .excludePathPatterns("/admin")
                //排除登录验证的方法
                .excludePathPatterns("/admin/login");
    }
}
