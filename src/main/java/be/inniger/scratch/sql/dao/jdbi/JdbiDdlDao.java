package be.inniger.scratch.sql.dao.jdbi;

import be.inniger.scratch.sql.dao.DdlDao;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JdbiDdlDao implements DdlDao {

    private final Jdbi jdbi;

    @Override
    public void enforceForeignKeys() {
        jdbi.useHandle(handle ->
                handle.execute("PRAGMA FOREIGN_KEYS = ON"));
    }

    @Override
    public void dropPersonTable() {
        jdbi.useHandle(handle ->
                handle.execute("DROP TABLE IF EXISTS person"));
    }

    @Override
    public void createPersonTable() {
        jdbi.useHandle(handle ->
                handle.execute("""
                        CREATE TABLE person
                        (
                            id   INTEGER NOT NULL PRIMARY KEY,
                            name TEXT NOT NULL UNIQUE
                        )
                        """));
    }

    @Override
    public void dropAddressTable() {
        jdbi.useHandle(handle ->
                handle.execute("DROP TABLE IF EXISTS address"));
    }

    @Override
    public void createAddressTable() {
        jdbi.useHandle(handle ->
                handle.execute("""
                        CREATE TABLE address
                        (
                            id        INTEGER NOT NULL PRIMARY KEY,
                            street    TEXT NOT NULL,
                            person_id INTEGER NOT NULL,
                            FOREIGN KEY (person_id) REFERENCES person (id)
                        )
                        """));
    }
}
