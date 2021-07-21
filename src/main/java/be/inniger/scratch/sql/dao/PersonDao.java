package be.inniger.scratch.sql.dao;

import be.inniger.scratch.sql.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

    List<Person> findAll();

    Optional<Person> findById(int id);

    Optional<Person> findByName(String name);

    void save(Person person);

    void update(Person person);

    void deleteById(int id);
}
