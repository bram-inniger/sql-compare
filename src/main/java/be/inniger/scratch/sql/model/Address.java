package be.inniger.scratch.sql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Address {

    private final Integer id;
    private final String street;
    private final Integer personId;

    public Address(String street, Integer personId) {
        this(null, street, personId);
    }

    public Address(Integer id, String street, Integer personId) {
        this.id = id;
        this.street = street;
        this.personId = personId;
    }
}
