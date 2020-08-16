package com.hnust.movie.config;

import com.hnust.movie.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Title:springmvc的配置类，原本继承WebMvcConfigurerAdapter类，但过时了，用WebMvcConfigurationSupport来替换
 * @Author: ggh
 * @Date: 2020/5/25 20:43
 */

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //添加拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/login");

        super.addInterceptors(registry);
    }

    //添加静态资源配置，springboot2.x之后默认时会被拦截下来，在addInterceptors方法中排除也没有用
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/static/").addResourceLocations("classpath:/public/");

        super.addResourceHandlers(registry);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login.html").setViewName("login");

        super.addViewControllers(registry);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
