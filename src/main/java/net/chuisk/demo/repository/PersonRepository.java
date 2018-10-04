package net.chuisk.demo.repository;

import net.chuisk.demo.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {
	Mono<Person> getPerson(int id);

	Flux<Person> allPerson();

	Mono<String> savePerson(Mono<Person> person);
	// Mono<Person> savePerson(Mono<Person> person);

}
