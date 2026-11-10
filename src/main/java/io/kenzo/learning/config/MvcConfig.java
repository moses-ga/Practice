package io.kenzo.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /** Honour X-Forwarded-* headers when running behind a reverse proxy. */
    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

    /** Serves files saved under ./uploads at /uploads/** (for future file uploads). */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirname = "uploads";
        Path uploadDir = Paths.get(dirname);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/" + dirname + "/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}
