package com.momo2x.momobank.gatewayserver.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.Service.ACCOUNTS;
import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.Service.CARDS;
import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.Service.LOANS;
import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.BASE_URI;
import static java.lang.String.format;

@Configuration
public class GatewayRouteConfig {
    @Bean
    public RouteLocator routeLocator(final RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(serviceRouteDefinition(ACCOUNTS))
                .route(serviceRouteDefinition(CARDS))
                .route(serviceRouteDefinition(LOANS))
                .build();
    }

    private static Function<PredicateSpec, Buildable<Route>> serviceRouteDefinition(final String service) {
        return predicateSpec -> predicateSpec
                .path(format("/%s/%s/**", BASE_URI, service))
                .filters(gatewayFilterSpec -> gatewayFilterSpec
                        .rewritePath(
                                format("/%s/%s/(?<segment>.*)", BASE_URI, service),
                                "/${segment}"))
                .uri(format("lb://%s", service.toUpperCase()));
    }
}
