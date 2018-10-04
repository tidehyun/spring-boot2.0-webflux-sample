package net.chuisk.demo.handler;

import lombok.extern.slf4j.Slf4j;
import net.chuisk.demo.model.Person;
import net.chuisk.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@Slf4j
public class RestHandler {

    @Autowired
    private PersonRepository repository;

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.valueOf(request.pathVariable("id"));
        log.info("person id : {}", personId);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Person> personMono = this.repository.getPerson(personId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(personMono, Person.class).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        // request.body(BodyExtractors.toMono(Person.class));
        return ServerResponse.ok().body(repository.savePerson(request.bodyToMono(Person.class)), String.class);
    }

    public Mono<ServerResponse> getAllPerson(ServerRequest request) {
        Flux<Person> people = this.repository.allPerson();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
    }

}
