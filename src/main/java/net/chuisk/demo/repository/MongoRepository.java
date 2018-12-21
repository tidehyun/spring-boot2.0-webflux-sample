package net.chuisk.demo.repository;

import net.chuisk.demo.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends ReactiveMongoRepository<Person, String> {
}
