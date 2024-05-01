package com.rentstate.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user_service_route", r -> r.path("/api/v1/users/**")
						.uri("lb://user-service"))
				.route("property_service_route", r -> r.path("/api/v1/properties/**")
						.uri("lb://property-service"))
				.route("post_service_route", r -> r.path("/api/v1/posts/**", "/api/v1/comments/**")
						.uri("lb://post-service"))
				.build();
	}
}
