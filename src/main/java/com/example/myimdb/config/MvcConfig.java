package com.example.myimdb.config;

import com.example.myimdb.authorization.interceptor.AuthorizationInterceptor;
import com.example.myimdb.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置类，增加自定义拦截器和解析器
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                  // 允许跨域请求的path，支持路径通配符，如：/api/**
                .allowedOrigins("*")                    // 允许发起请求的源
                .allowedHeaders("*")                    // 允许客户端的提交的 Header，通配符 * 可能有浏览器兼容问题
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")                 // 允许客户端使用的请求方法
                .allowCredentials(false)                // 不允许携带凭证信息
        ;
    }
}
