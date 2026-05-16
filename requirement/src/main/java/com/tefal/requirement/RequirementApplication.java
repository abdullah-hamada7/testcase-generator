package com.tefal.requirement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Requirement microservice REST API Documentation",
                description = "Testcase Generator Requirement microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Mohamed Abdou",
                        email = "tmo090461@gmail.com"
                )
        )
)
@EnableFeignClients
public class RequirementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequirementApplication.class, args);
    }

}
