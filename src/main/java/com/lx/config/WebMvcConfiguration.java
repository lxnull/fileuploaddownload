package com.lx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问的路径：注意 ”/**“（相当于key或者映射）
        // 上传资源的路径：真实路径最后 + ”/“（相当于val或者资源）
        registry.addResourceHandler("/uploaded/**").addResourceLocations("file:G:\\JavaSpace\\Java进阶\\fileuploaddownload\\src\\main\\resources\\static\\file\\");
    }
}
