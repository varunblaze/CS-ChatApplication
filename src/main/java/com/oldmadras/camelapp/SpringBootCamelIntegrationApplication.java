package com.oldmadras.camelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.*;
import org.springframework.boot.autoconfigure.*;
@SpringBootApplication
@ComponentScan("com.oldmadras")
@EnableAutoConfiguration
public class SpringBootCamelIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCamelIntegrationApplication.class, args);
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
        		registry.addMapping("/**");
            }
        };
    }
}