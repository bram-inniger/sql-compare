package be.inniger.scratch.sql.dao.jdbctemplate;

import be.inniger.scratch.sql.dao.PersonDao;
import be.inniger.scratch.sql.model.Person;
import be.inniger.scratch.sql.util.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JdbcTemplatePersonDao implements PersonDao {

    private final NamedParameterJdbcTemplate template;

    @Override
    public List<Person> findAll() {
        return template.query(
                "SELECT * FROM person",
                Map.of(),
                this::personMapper);
    }

    @Override
    public Optional<Person> findById(int id) {
        return FunctionUtil.trySingle(template.query(
                "SELECT * FROM person WHERE id = :id",
                Map.of("id", id),
                this::personMapper));
    }

    @Override
    public Optional<Person> findByName(String name) {
        return FunctionUtil.trySingle(template.query(
                "SELECT * FROM person WHERE name = :name",
                Map.of("name", name),
                this::personMapper));
    }

    @Override
    public void save(Person person) {
        //noinspection SqlInsertValues
        template.update(
                "INSERT INTO person(name) VALUES (:name)",
                Map.of("name", person.getName()));
    }

    @Override
    public void update(Person person) {
        template.update(
                "UPDATE person SET name = :name WHERE id = :id",
                Map.of(
                        "name", person.getName(),
                        "id", person.getId()));
    }

    @Override
    public void deleteById(int id) {
        template.update(
                "DELETE FROM person WHERE id = :id",
                Map.of("id", id));
    }

    private Person personMapper(ResultSet rs, int __) throws SQLException {
        return new Person(
                rs.getInt("id"),
                rs.getString("name"));
    }
}
