package com.pratifolio.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route("user-service",r->r.path("/user/**")
						.uri("http://localhost:8080/"))
				.route("product-service",r->r.path("/product/**")
						.uri("http://localhost:8081/"))
				.route("order-service",r->r.path("/order/**")
						.uri("http://localhost:8082"))
				.route("notification-service",r->r.path("/notify/**")
						.uri("http://localhost:8083"))
				.build();
	}

}
