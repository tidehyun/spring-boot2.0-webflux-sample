package net.chuisk.demo.handler;

import lombok.extern.slf4j.Slf4j;
import net.chuisk.demo.model.ErrorModel;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ErrorHandler {
    public <T extends ServerResponse> Mono<ServerResponse> error1(ServerRequest serverRequest) {
        return getData(serverRequest.pathVariable("stat"))
                .onErrorReturn(ErrorModel.builder().code(500).description("unknown error").build())
                .flatMap(s -> ServerResponse.ok().syncBody(s));
    }

    public Mono<ServerResponse> error2(ServerRequest serverRequest) {
        return getData(serverRequest.pathVariable("stat"))
                .flatMap(s -> ServerResponse.ok().syncBody("Error2 Success"))
                .onErrorResume(throwable -> Mono.just(ErrorModel.builder().code(500).description("error2 Error!!").build())
                        .flatMap(s -> ServerResponse.ok().syncBody(s)));
    }

    private Mono<Object> getData(String stat) {
        return "S".equals(stat)
                ? Mono.just("SUCCESS")
                : Mono.error(new RuntimeException());
    }


    // global error handle
    public Mono<ServerResponse> error3(ServerRequest serverRequest) {
        return "S".equals(serverRequest.pathVariable("stat"))
                ? ServerResponse.ok().body(Mono.just("SUCCESS"), String.class)
                : Mono.error(new RuntimeException());
    }
}
