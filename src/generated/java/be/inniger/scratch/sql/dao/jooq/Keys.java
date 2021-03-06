/*
 * This file is generated by jOOQ.
 */
package be.inniger.scratch.sql.dao.jooq;


import be.inniger.scratch.sql.dao.jooq.tables.Address;
import be.inniger.scratch.sql.dao.jooq.tables.Person;
import be.inniger.scratch.sql.dao.jooq.tables.records.AddressRecord;
import be.inniger.scratch.sql.dao.jooq.tables.records.PersonRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AddressRecord> PK_ADDRESS = UniqueKeys0.PK_ADDRESS;
    public static final UniqueKey<PersonRecord> PK_PERSON = UniqueKeys0.PK_PERSON;
    public static final UniqueKey<PersonRecord> SQLITE_AUTOINDEX_PERSON_1 = UniqueKeys0.SQLITE_AUTOINDEX_PERSON_1;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AddressRecord, PersonRecord> FK_ADDRESS_PERSON_1 = ForeignKeys0.FK_ADDRESS_PERSON_1;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<AddressRecord> PK_ADDRESS = Internal.createUniqueKey(Address.ADDRESS, "pk_address", new TableField[] { Address.ADDRESS.ID }, true);
        public static final UniqueKey<PersonRecord> PK_PERSON = Internal.createUniqueKey(Person.PERSON, "pk_person", new TableField[] { Person.PERSON.ID }, true);
        public static final UniqueKey<PersonRecord> SQLITE_AUTOINDEX_PERSON_1 = Internal.createUniqueKey(Person.PERSON, "sqlite_autoindex_person_1", new TableField[] { Person.PERSON.NAME }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<AddressRecord, PersonRecord> FK_ADDRESS_PERSON_1 = Internal.createForeignKey(Keys.PK_PERSON, Address.ADDRESS, "fk_address_person_1", new TableField[] { Address.ADDRESS.PERSON_ID }, true);
    }
}
