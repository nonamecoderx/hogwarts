package com.example.homework_hogwards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HomeworkHogwardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkHogwardsApplication.class, args);
    }

}
