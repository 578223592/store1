package com.swx.store1.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Admin
 * Date: 2021/11/3 18:03
 * FileName: InterceptorConfig
 * Description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象
        HandlerInterceptor loginInterceptor = new LoginInterceptor();
        // 白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        //注意自己控制器的mapping和案例里面不一样，这里面要对应的修改为自己的
        patterns.add("/users/register");
        patterns.add("/users/login");
//        patterns.add("/users/**");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        // 通过注册工具添加拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }


}
