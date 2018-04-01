package pl.mkokoszka.mydbapplication.repository;

import java.util.List;

import pl.mkokoszka.mydbapplication.domain.Person;

public interface PersonRepository {

    void saveOrUpdate(Person person);
    void delete(Person person);
    List<Person> findAll();
}
