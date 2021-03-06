/*
 * This file is generated by jOOQ.
 */
package be.inniger.scratch.sql.dao.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Address implements Serializable {

    private static final long serialVersionUID = -1653895161;

    private final Integer id;
    private final String  street;
    private final Integer personId;

    public Address(Address value) {
        this.id = value.id;
        this.street = value.street;
        this.personId = value.personId;
    }

    public Address(
        Integer id,
        String  street,
        Integer personId
    ) {
        this.id = id;
        this.street = street;
        this.personId = personId;
    }

    public Integer getId() {
        return this.id;
    }

    public String getStreet() {
        return this.street;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Address (");

        sb.append(id);
        sb.append(", ").append(street);
        sb.append(", ").append(personId);

        sb.append(")");
        return sb.toString();
    }
}
