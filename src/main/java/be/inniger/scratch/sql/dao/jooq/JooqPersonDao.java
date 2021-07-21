package be.inniger.scratch.sql.dao.jooq;

import be.inniger.scratch.sql.dao.PersonDao;
import be.inniger.scratch.sql.dao.jooq.tables.records.PersonRecord;
import be.inniger.scratch.sql.model.Person;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static be.inniger.scratch.sql.dao.jooq.Tables.PERSON;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JooqPersonDao implements PersonDao {

    private final DSLContext db;

    @Override
    public List<Person> findAll() {
        return db.selectFrom(PERSON)
                .fetch(this::personMapper);
    }

    @Override
    public Optional<Person> findById(int id) {
        return db.selectFrom(PERSON)
                .where(PERSON.ID.eq(id))
                .fetchOptional()
                .map(this::personMapper);
    }

    @Override
    public Optional<Person> findByName(String name) {
        return db.selectFrom(PERSON)
                .where(PERSON.NAME.eq(name))
                .fetchOptional()
                .map(this::personMapper);
    }

    @Override
    public void save(Person person) {
        db.insertInto(PERSON, PERSON.NAME)
                .values(person.getName())
                .execute();
    }

    @Override
    public void update(Person person) {
        db.update(PERSON)
                .set(PERSON.NAME, person.getName())
                .where(PERSON.ID.eq(person.getId()))
                .execute();
    }

    @Override
    public void deleteById(int id) {
        db.deleteFrom(PERSON)
                .where(PERSON.ID.eq(id))
                .execute();
    }

    private Person personMapper(PersonRecord record) {
        return new Person(
                record.getId(),
                record.getName());
    }
}
