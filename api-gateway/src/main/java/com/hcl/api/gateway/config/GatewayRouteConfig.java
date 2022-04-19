package com.hcl.api.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Autowired
    JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("USER-SERVICE", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))

                .route("AUTH-SERVER", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTH-SERVER"))
                
                .route("PRODUCT-SERVICE", r -> r.path("/products/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PRODUCT-SERVICE"))
                
                .route("ORDER-SERVICE", r -> r.path("/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ORDER-SERVICE"))
                .build();
    }
}
