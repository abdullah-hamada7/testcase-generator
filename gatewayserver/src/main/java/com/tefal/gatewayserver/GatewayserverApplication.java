package com.tefal.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator testcaseRouteConfig(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r-> r
                        .path("/testcasegenerator/projects/**")
                        .filters( f -> f.rewritePath(
                                        "/testcasegenerator/projects/(?<segment>.*)",
                                        "/${segment}"
                                )
                                .addResponseHeader("X-Response-Time" , LocalDateTime.now().toString()))
                                .uri("lb://PROJECTS"))
                .route(r-> r
                        .path("/testcasegenerator/requirements/**")
                        .filters( f -> f.rewritePath(
                                        "/testcasegenerator/requirements/(?<segment>.*)",
                                        "/${segment}"
                                )
                                .addResponseHeader("X-Response-Time" , LocalDateTime.now().toString()))
                        .uri("lb://REQUIREMENT"))
                .route( r -> r
                        .path("/testcasegenerator/testcases/**")
                        .filters(f -> f
                                .rewritePath(
                                        "/testcasegenerator/testcases/(?<segment>.*)",
                                        "/${segment}"
                                )
                                .addResponseHeader(
                                        "X-Response-Time",
                                        LocalDateTime.now().toString()
                                ))
                        .uri("lb://TESTCASE"))
                .route("ai-service", r -> r
                        .path("/testcasegenerator/ai/**")
                        .filters(f -> f
                                .rewritePath(
                                        "/testcasegenerator/ai/(?<segment>.*)",
                                        "/${segment}"
                                )
                                .addResponseHeader(
                                        "X-Response-Time",
                                        LocalDateTime.now().toString()
                                ))
                                .uri("lb://AI-SERVICE"))
                .build();
    }

}
