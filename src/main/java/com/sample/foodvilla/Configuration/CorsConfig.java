package com.sample.foodvilla.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for your API endpoints
                .allowedOrigins("http://localhost:4200") // Adjust the origin to match your Angular app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Add the allowed HTTP methods
    }
}
