package com.hatefulbug.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> paymentServiceRoutes() {
        return GatewayRouterFunctions.route("payment_service")
                .route(RequestPredicates.path("/api/v1/payment/paymentMethods"), HandlerFunctions.http("http://localhost:8090"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ecommerceServiceRoutes() {
        return GatewayRouterFunctions.route("ecommerce_service")
                .route(RequestPredicates.path("/api/v1/ecommerce/products/all"), HandlerFunctions.http("http://localhost:8091"))
                .build();
    }

}
