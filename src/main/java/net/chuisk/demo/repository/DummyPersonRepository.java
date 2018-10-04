package net.chuisk.demo.repository;

import lombok.extern.slf4j.Slf4j;
import net.chuisk.demo.model.Person;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class DummyPersonRepository implements PersonRepository {

    private final Map<Integer, Person> personMap = new HashMap<Integer, Person>();

    public Mono<Person> getPerson(int id) {
        return Mono.justOrEmpty(this.personMap.get(id));
    }

    public Flux<Person> allPerson() {
        return Flux.fromIterable(this.personMap.values());
    }

    public Mono<String> savePerson(Mono<Person> personMono) {
        return personMono.doOnNext(person -> {
            int id = personMap.size() + 1;
            log.info("param : {}", person);
            personMap.put(id, person);
        }).thenEmpty(Mono.empty()).thenReturn("success");
    }

    // public Mono<Person> savePerson(Mono<Person> personMono) {
    // return personMono.doOnNext(person -> {
    // int id = people.size() + 1;
    // people.put(id, person);
    // });
    // }

}
