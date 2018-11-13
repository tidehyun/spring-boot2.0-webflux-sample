package net.chuisk.demo.controller.functional;

import net.chuisk.demo.handler.ErrorHandler;
import net.chuisk.demo.handler.RestHandler;
import net.chuisk.demo.handler.ViewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class HelloRoute implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> restRoutes(RestHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/functional/person/{id}"), handler::getPerson)
                .andRoute(RequestPredicates.GET("/functional/person"), handler::getAllPerson)
                .andRoute(RequestPredicates.GET("/functional/test"), handler::test)
                .andRoute(RequestPredicates.POST("/functional/person"), handler::createPerson)
                .andRoute(RequestPredicates.POST("/functional/upload"), handler::upload);
    }

    @Bean
    public RouterFunction<ServerResponse> viewRoutes(ViewHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.path("/functional").or(RequestPredicates.path("/functional/main")),
                        handler::main)
                .andRoute(RequestPredicates.GET("/functional/register"), handler::regPerson)
                .andRoute(RequestPredicates.GET("/functional/upload"), handler::upload);
    }

    @Bean
    public RouterFunction<ServerResponse> errorRoutes(ErrorHandler handler) {
        return RouterFunctions.route(RequestPredicates.path("/error1/{stat}"), handler::error1)
                .andRoute(RequestPredicates.path("/error2/{stat}"), handler::error2)
                .andRoute(RequestPredicates.path("/error3/{stat}"), handler::error3);


    }

}
