package net.chuisk.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.chuisk.demo.model.ErrorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalErrorConfig {

    @Autowired
    ObjectMapper mapper;

    @Bean
    @Order(-2)
    public WebExceptionHandler exceptionHandler() {
        return (ServerWebExchange exchange, Throwable t) -> handle1(exchange, t);
    }

    private Mono<Void> handle1(ServerWebExchange exchange, Throwable t) {
//        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().allocateBuffer();
        try {
            dataBuffer.write(mapper.writeValueAsString(ErrorModel.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(t.getMessage()).build()).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
//        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
//
//        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().allocateBuffer();
//
//        try {
//            dataBuffer.write(mapper.writeValueAsString(ErrorModel.builder().code(500).name("global error").build()).getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
//    }
}
