package com.tefal.projects;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Projects microservice REST API Documentation",
                description = "Testcase Generator Projects microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Mohamed Abdou",
                        email = "tmo090461@gmail.com"
                )
        )
)
public class ProjectsApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjectsApplication.class, args);
    }

}
