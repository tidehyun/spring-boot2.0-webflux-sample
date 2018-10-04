package net.chuisk.demo.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class ViewHandler {

    public Mono<ServerResponse> main(ServerRequest request) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("msg", "functional based reactive web");
        dataMap.put("say", "hi~~");
        return ServerResponse.ok().render("main", dataMap);
    }

    public Mono<ServerResponse> regPerson(ServerRequest request) {
        return ServerResponse.ok().render("functionalRegPerson");
    }

}
