package be.inniger.scratch.sql.dao.jdbctemplate;

import be.inniger.scratch.sql.dao.DdlDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcTemplateDdlDao implements DdlDao {

    private final JdbcTemplate template;

    public JdbcTemplateDdlDao(NamedParameterJdbcTemplate namedTemplate) {
        this.template = namedTemplate.getJdbcTemplate();
    }

    @Override
    public void enforceForeignKeys() {
        template.execute("PRAGMA FOREIGN_KEYS = ON");
    }

    @Override
    public void dropPersonTable() {
        template.execute("DROP TABLE IF EXISTS person");
    }

    @Override
    public void createPersonTable() {
        template.execute("""
                CREATE TABLE person
                (
                    id   INTEGER NOT NULL PRIMARY KEY,
                    name TEXT NOT NULL UNIQUE
                )
                """);
    }

    @Override
    public void dropAddressTable() {
        template.execute("DROP TABLE IF EXISTS address");
    }

    @Override
    public void createAddressTable() {
        template.execute("""
                CREATE TABLE address
                (
                    id        INTEGER NOT NULL PRIMARY KEY,
                    street    TEXT NOT NULL,
                    person_id INTEGER NOT NULL,
                    FOREIGN KEY (person_id) REFERENCES person (id)
                )
                """);
    }
}
