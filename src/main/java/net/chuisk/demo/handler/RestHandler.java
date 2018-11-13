package net.chuisk.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.chuisk.demo.model.ErrorModel;
import net.chuisk.demo.model.Person;
import net.chuisk.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@Slf4j
public class RestHandler {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private Validator validator;

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.valueOf(request.pathVariable("id"));
        log.info("person id : {}", personId);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Person> personMono = this.repository.getPerson(personId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(personMono, Person.class).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request.bodyToMono(Person.class).flatMap(person -> {
            String message = validation(person);
            if (message == null) {
                return ServerResponse.ok().body(repository.savePerson(Mono.just(person)), String.class);
            } else {
                return Mono.error(new RuntimeException(message));
            }
        });
    }

    public Mono<ServerResponse> getAllPerson(ServerRequest request) {
        Flux<Person> people = this.repository.allPerson();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
    }


    public Mono<ServerResponse> test(ServerRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = validation(objectMapper.convertValue(request.queryParams().toSingleValueMap(), Person.class));
        return message == null ?
                ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just("Test"), String.class) :
                Mono.error(new RuntimeException(message));
    }

    private <T> String validation(T t) {
        Iterator<ConstraintViolation<T>> it = validator.validate(t).iterator();
        String message = null;
        while (it.hasNext()) {
            ConstraintViolation<T> constraintViolation = it.next();
            log.info("Error message : {}", constraintViolation.getMessage());
            message = constraintViolation.getMessage();
        }
        return message;
    }

    public Mono<ServerResponse> upload(ServerRequest request) {
        return request.body(BodyExtractors.toMultipartData()).flatMap(p -> {
            p.toSingleValueMap().keySet().stream().forEach(c -> {
                FilePart fp = (FilePart) p.toSingleValueMap().get(c);
                fp.transferTo(new File("/Users/ykh/dev/tmp/".concat(fp.filename())));
            });
            return ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just("SUCCESS"), String.class);
        }).onErrorResume(throwable ->
                Mono.just(ErrorModel.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description("File Upload Error").build())
                        .flatMap(s -> ServerResponse.ok().syncBody(s)));
    }
}
