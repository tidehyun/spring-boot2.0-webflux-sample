package net.chuisk.demo.controller.annotation;

import net.chuisk.demo.model.Person;
import net.chuisk.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloRestController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/annotation/person/{id}")
    public Mono<Person> getPerson(@PathVariable("id") String id) {
        return personRepository.getPerson(Integer.valueOf(id));
    }

    @GetMapping("/annotation/person")
    public Flux<Person> getAllPerson() {
        return personRepository.allPerson();
    }

    @PostMapping("/annotation/person")
    public Mono<String> savePerson(@RequestBody Mono<Person> person) {
        return personRepository.savePerson(person);
    }

}
