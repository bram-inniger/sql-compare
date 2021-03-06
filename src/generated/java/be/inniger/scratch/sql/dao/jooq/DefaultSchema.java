/*
 * This file is generated by jOOQ.
 */
package be.inniger.scratch.sql.dao.jooq;


import be.inniger.scratch.sql.dao.jooq.tables.Address;
import be.inniger.scratch.sql.dao.jooq.tables.Person;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 417438332;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>address</code>.
     */
    public final Address ADDRESS = Address.ADDRESS;

    /**
     * The table <code>person</code>.
     */
    public final Person PERSON = Person.PERSON;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Address.ADDRESS,
            Person.PERSON);
    }
}
