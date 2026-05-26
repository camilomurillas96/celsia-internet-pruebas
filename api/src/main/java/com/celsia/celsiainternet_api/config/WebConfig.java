package com.celsia.celsiainternet_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todos los endpoints de la API
                .allowedOrigins("*") // Permite peticiones desde cualquier origen (React, Angular, Postman, etc.)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Permite cualquier cabecera en la petición

        /*
         * PRODUCCIÓN real,
         * el allowedOrigins no debe ser "*", sino la URL exacta
         * del frontend (ej. "https://mi-app-celsia.com").
         */
    }
}