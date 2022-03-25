package com.lx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问的路径：注意 ”/**“（相当于key或者映射）
        // 上传资源的路径：真实路径最后 + ”/“（相当于val或者资源）
        registry.addResourceHandler(staticPatternPath).addResourceLocations("file:" + uploadFolder);
    }
}
