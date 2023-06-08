package com.example.javaweb_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class JavaWebBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaWebBeApplication.class, args);
    }

}
