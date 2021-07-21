package be.inniger.scratch.sql.dao.mybatis;

import be.inniger.scratch.sql.dao.DdlDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface MyBatisDdlMapper extends DdlDao {

    @Override
    @Update("PRAGMA FOREIGN_KEYS = ON")
    void enforceForeignKeys();

    @Override
    @Update("DROP TABLE IF EXISTS person")
    void dropPersonTable();

    @Override
    @Insert("""
                CREATE TABLE person
                (
                    id   INTEGER NOT NULL PRIMARY KEY,
                    name TEXT NOT NULL UNIQUE
                )
            """)
    void createPersonTable();

    @Override
    @Update("DROP TABLE IF EXISTS address")
    void dropAddressTable();

    @Override
    @Insert("""
                CREATE TABLE address
                (
                    id        INTEGER NOT NULL PRIMARY KEY,
                    street    TEXT NOT NULL,
                    person_id INTEGER NOT NULL,
                    FOREIGN KEY (person_id) REFERENCES person (id)
                )
            """)
    void createAddressTable();
}
