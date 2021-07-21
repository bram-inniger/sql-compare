package be.inniger.scratch.sql.dao.jdbi;

import be.inniger.scratch.sql.dao.PersonDao;
import be.inniger.scratch.sql.model.Person;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JdbiPersonDao implements PersonDao {

    private final Jdbi jdbi;

    @Override
    public List<Person> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM person")
                        .mapTo(Person.class)
                        .list());
    }

    @Override
    public Optional<Person> findById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM person WHERE id = :id")
                        .bind("id", id)
                        .mapTo(Person.class)
                        .findOne());
    }

    @Override
    public Optional<Person> findByName(String name) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM person WHERE name = :name")
                        .bind("name", name)
                        .mapTo(Person.class)
                        .findOne());
    }

    @Override
    public void save(Person person) {
        //noinspection SqlInsertValues
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO person(name) VALUES (:name)")
                        .bind("name", person.getName())
                        .execute());
    }

    @Override
    public void update(Person person) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE person SET name = :name WHERE id = :id")
                        .bind("name", person.getName())
                        .bind("id", person.getId())
                        .execute());
    }

    @Override
    public void deleteById(int id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM person WHERE id = :id")
                        .bind("id", id)
                        .execute());
    }
}
