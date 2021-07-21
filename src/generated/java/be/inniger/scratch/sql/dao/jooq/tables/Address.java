/*
 * This file is generated by jOOQ.
 */
package be.inniger.scratch.sql.dao.jooq.tables;


import be.inniger.scratch.sql.dao.jooq.DefaultSchema;
import be.inniger.scratch.sql.dao.jooq.Keys;
import be.inniger.scratch.sql.dao.jooq.tables.records.AddressRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Address extends TableImpl<AddressRecord> {

    private static final long serialVersionUID = -790675456;

    /**
     * The reference instance of <code>address</code>
     */
    public static final Address ADDRESS = new Address();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AddressRecord> getRecordType() {
        return AddressRecord.class;
    }

    /**
     * The column <code>address.id</code>.
     */
    public final TableField<AddressRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>address.street</code>.
     */
    public final TableField<AddressRecord, String> STREET = createField(DSL.name("street"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>address.person_id</code>.
     */
    public final TableField<AddressRecord, Integer> PERSON_ID = createField(DSL.name("person_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>address</code> table reference
     */
    public Address() {
        this(DSL.name("address"), null);
    }

    /**
     * Create an aliased <code>address</code> table reference
     */
    public Address(String alias) {
        this(DSL.name(alias), ADDRESS);
    }

    /**
     * Create an aliased <code>address</code> table reference
     */
    public Address(Name alias) {
        this(alias, ADDRESS);
    }

    private Address(Name alias, Table<AddressRecord> aliased) {
        this(alias, aliased, null);
    }

    private Address(Name alias, Table<AddressRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Address(Table<O> child, ForeignKey<O, AddressRecord> key) {
        super(child, key, ADDRESS);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<AddressRecord> getPrimaryKey() {
        return Keys.PK_ADDRESS;
    }

    @Override
    public List<UniqueKey<AddressRecord>> getKeys() {
        return Arrays.<UniqueKey<AddressRecord>>asList(Keys.PK_ADDRESS);
    }

    @Override
    public List<ForeignKey<AddressRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AddressRecord, ?>>asList(Keys.FK_ADDRESS_PERSON_1);
    }

    public Person person() {
        return new Person(this, Keys.FK_ADDRESS_PERSON_1);
    }

    @Override
    public Address as(String alias) {
        return new Address(DSL.name(alias), this);
    }

    @Override
    public Address as(Name alias) {
        return new Address(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Address rename(String name) {
        return new Address(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Address rename(Name name) {
        return new Address(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}