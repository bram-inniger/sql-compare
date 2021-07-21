package be.inniger.scratch.sql.dao;

public interface DdlDao {

    void enforceForeignKeys();

    void dropPersonTable();

    void createPersonTable();

    void dropAddressTable();

    void createAddressTable();
}
