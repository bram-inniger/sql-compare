package be.inniger.scratch.sql.dao.jooq;

import be.inniger.scratch.sql.dao.DdlDao;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static be.inniger.scratch.sql.dao.jooq.Tables.ADDRESS;
import static be.inniger.scratch.sql.dao.jooq.Tables.PERSON;
import static org.jooq.Nullability.NOT_NULL;
import static org.jooq.impl.DSL.foreignKey;
import static org.jooq.impl.DSL.primaryKey;
import static org.jooq.impl.DSL.unique;
import static org.jooq.impl.SQLDataType.CLOB;
import static org.jooq.impl.SQLDataType.INTEGER;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JooqDdlDao implements DdlDao {

    private final DSLContext db;

    @Override
    public void enforceForeignKeys() {
        db.execute("PRAGMA FOREIGN_KEYS = ON");
    }

    @Override
    public void dropPersonTable() {
        db.dropTableIfExists(PERSON)
                .execute();
    }

    @Override
    public void createPersonTable() {
        db.createTable(PERSON)
                .column(PERSON.ID, INTEGER.identity(true))
                .column(PERSON.NAME, CLOB.nullability(NOT_NULL))
                .constraints(
                        primaryKey(PERSON.ID),
                        unique(PERSON.NAME))
                .execute();
    }

    @Override
    public void dropAddressTable() {
        db.dropTableIfExists(ADDRESS)
                .execute();
    }

    @Override
    public void createAddressTable() {
        db.createTable(ADDRESS)
                .column(ADDRESS.ID, INTEGER.identity(true))
                .column(ADDRESS.STREET, CLOB.nullability(NOT_NULL))
                .column(ADDRESS.PERSON_ID, INTEGER.nullability(NOT_NULL))
                .constraints(
                        primaryKey(PERSON.ID),
                        foreignKey(ADDRESS.PERSON_ID).references(PERSON))
                .execute();
    }
}
